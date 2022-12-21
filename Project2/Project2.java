import java.io.IOException;
import java.util.Date;
import java.util.List;
import twitter.common.GetConfiguration;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class GetUserTimeline{
    publicstatic void main(String[] args)throws TwitterException, IOException {
        GetConfiguration conf = new GetConfiguration();
        Twitter twitter = conf.getNewInstance();
        List<Status> statuses;
        String user = "Tianhao Yao";
        Paging page = new Paging();
        page.count(50);
        statuses = twitter.getUserTimeline(user, page);
        System.out.println("Showing@"+ user + "'suser timeline.");
        for (Status status : statuses) {
            Stringcontent = status.getText(); // get the text of the tweet
            StringScreenName = status.getUser().getScreenName();
            Date publishDate =  status.getCreatedAt();
            StringtweetUrl = "https://twitter.com/"+ status.getUser().getScreenName() + "/status/"+ status.getId();
            System.out.println("tweetUrl:"+ tweetUrl);
            if (status.getMediaEntities() !=null && status.getMediaEntities().length>=1) {
                try {
                    String type =status.getMediaEntities()[0].getType();
                    if (type.equals("photo")) {
                        String imgUrl =status.getMediaEntities()[0].getMediaURL();
                        System.out.println("imgUrl:"+ imgUrl);
                    } else if (type.equals("video")) {
                        String videoUrl =status.getMediaEntities()[0].getMediaURL();
                        System.out.println("videoUrl:"+ videoUrl);
                    } else {
                        String animatedGifUrl =status.getMediaEntities()[0].getMediaURL();
                        System.out.println("animatedGifUrl:"+ animatedGifUrl);
                    }

                } catch(Exception e) {
                    System.out.println("Status:"+ status);
                    System.out.println(e.getStackTrace());
                }
            }
            if (status.getRetweetedStatus() !=null) {
                System.out.println("sourceTweet:"+status.getRetweetedStatus());
                System.out.println(tweetUrl);
                StringreScreenName = status.getRetweetedStatus().getUser().getScreenName();
                Long RetweetedId =status.getRetweetedStatus().getId();
                StringretweetUrl = "https://twitter.com/"+ reScreenName
                        + "/status/" + RetweetedId;
                System.out.println("retweetUrl:"+retweetUrl);
            }
        }
    }
}
