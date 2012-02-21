import com.foursquare.json.traverser.JsonTraverser //, DynamicJsonTraverser}
import net.liftweb.json.JsonParser

object Test {
  def main(args: Array[String]): Unit = {
    val jsonStr = """{"id_str":"171331379555090432","coordinates":{"coordinates":[-73.77090964,40.75205260],"type":"Point"},"in_reply_to_status_id_str":"171331036926586883","place":{"name":"Queens","attributes":{},"full_name":"Queens, NY","place_type":"city","country":"United States","bounding_box":{"coordinates":[[[-74.042112,40.489794],[-73.700272,40.489794],[-73.700272,40.812242],[-74.042112,40.812242]]],"type":"Polygon"},"id":"b6ea2e341ba4356f","url":"http:\/\/api.twitter.com\/1\/geo\/id\/b6ea2e341ba4356f.json","country_code":"US"},"geo":{"coordinates":[40.75205260,-73.77090964],"type":"Point"},"in_reply_to_user_id_str":"3187821","favorited":false,"created_at":"Sun Feb 19 20:32:38 +0000 2012","user":{"id":3032241,"profile_background_image_url_https":"https:\/\/si0.twimg.com\/profile_background_images\/4689671\/bg.png","created_at":"Sat Mar 31 02:11:20 +0000 2007","profile_image_url":"http:\/\/a3.twimg.com\/profile_images\/1776174244\/image1327348450_normal.png","profile_text_color":"663B12","time_zone":"Eastern Time (US & Canada)","following":true,"followers_count":1327,"profile_sidebar_border_color":"C6E2EE","screen_name":"bdotdub","verified":false,"utc_offset":-18000,"default_profile_image":false,"location":"New York, NY","name":"Benny Wong","notifications":false,"profile_background_tile":true,"favourites_count":207,"id_str":"3032241","profile_sidebar_fill_color":"c2e4a0","contributors_enabled":false,"protected":false,"show_all_inline_media":true,"profile_image_url_https":"https:\/\/si0.twimg.com\/profile_images\/1776174244\/image1327348450_normal.png","profile_background_color":"c4edc8","description":"building time machine as cofounder of @timehop) w\/ @jwegener \u2022 built @ExitStrategyNYC iPhone app \u2022 formerly built @GiltCity","default_profile":false,"lang":"en","is_translator":false,"profile_background_image_url":"http:\/\/a0.twimg.com\/profile_background_images\/4689671\/bg.png","statuses_count":8596,"geo_enabled":true,"profile_link_color":"18b500","follow_request_sent":false,"friends_count":546,"listed_count":84,"profile_use_background_image":true,"url":"http:\/\/bwong.net"},"in_reply_to_screen_name":"jwegener","in_reply_to_user_id":3187821,"retweeted":false,"in_reply_to_status_id":171331036926586883,"contributors":null,"truncated":false,"source":"\u003Ca href=\"http:\/\/twitter.com\/#!\/download\/iphone\" rel=\"nofollow\"\u003ETwitter for iPhone\u003C\/a\u003E","id":171331379555090432,"retweet_count":0,"text":"@jwegener @msingleton @ericfriedman agrizzle. Eric was building subscribetoit for @msg"}"""
    val json = JsonParser.parse(jsonStr)

    // Using Lift extractors
    implicit val formats = net.liftweb.json.DefaultFormats
    val coords1 = json \ "geo" \ "coordinates"
    val xy1 =
      try {
        val x = coords1.children(0).extract[Double]
        val y = coords1.children(1).extract[Double]
        Some((x, y))
      } catch {
        case ex: Exception => None
      }

    // Using JsonTraverser
    val tjson = JsonTraverser(json)
    val coords2 = tjson("geo")("coordinates")

    val xy2 =
      for {
        x <- coords2(0).getResult[Double]
        y <- coords2(1).getResult[Double]
      } yield (x, y)

    // Using DynamicJsonTraverser
  //   val djson = DynamicJsonTraverser(json)
  //   val coords3 = djson.geo.coordinates
  //   val xy3 =
  //     for {
  //       x <- coords3(0).getResult[Double]
  //       y <- coords3(1).getResult[Double]
  //     } yield (x, y)
  }
}
