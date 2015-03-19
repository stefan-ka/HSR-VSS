import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ChirpLoadTest extends Simulation {

  // Number of users, you may have to adapt this value depending on your environment.
  val noUsers = 1000

  // Change this URL if you want to generate load for a remote Chirp server
  val chirpUrl = "http://localhost:9000"

  val httpConf = http
    .baseURL(chirpUrl) // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    .disableFollowRedirect

  // User ID generator, creates user ids 100, 110, 120 etc. up to 1000
  val userIds = (1 to 1000 by 10).map(id => Map("userId" -> id)).toArray.circular

  val getMainPage = exec(
      http("get main page")
        .get("/")
    )

  val postCredentials = exec(
      http("post credentials")
        .post("/login")
        .formParam("userId", "${userId}")
        .check(status.is(303))
    )

  val postTweet = exec(
      http("post tweet")
        .post("/tweets")
        .formParam("content", "Lol http://img...")
        .check(status.is(303))
    )

  val getTimeline = exec(
      http("get timeline")
        .get("/tweets")
    )

  val logout = exec(
      http("logout")
        .get("/logout")
      .check(status.is(303))
    )

  val defaultScenario = 
    scenario("default user")
    .feed(userIds)
    .during(30 seconds){
      exec(getMainPage)
      .pause(1 seconds)
      .randomSwitch(
        90.0 -> 
          exec(postCredentials)
          .exec(getTimeline)
          .pause(3 seconds)
          .randomSwitch(
            3.0 -> 
              exec(postTweet)
              .exec(getTimeline)
              .pause(300 millis)
          )
          .exec(logout)
      )
      .pause(1 seconds)
    }
    
  val simulation = defaultScenario
  	.inject(rampUsers(noUsers) over (5 seconds))
  	.protocols(httpConf)
  	.exponentialPauses 

  setUp(simulation)
}