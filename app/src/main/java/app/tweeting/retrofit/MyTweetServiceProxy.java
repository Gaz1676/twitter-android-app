package app.tweeting.retrofit;

import java.util.List;

import app.tweeting.models.Tweet;
import app.tweeting.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

// Retrofit turns the HTTP API into a Java interface
// Annotations on the interface methods and its parameters indicate how a request will be handled
// Every method must have an HTTP annotation that provides the request method and relative URL
// Here we use three built-in annotations: GET, POST & DELETE
// The relative URL of the resource is specified in the annotation
// An object can also be specified for use as an HTTP request body with the @Body annotation

public interface MyTweetServiceProxy {


    //------ Tweets API
    @POST("/api/tweets")
    Call<Tweet> createTweet(@Body Tweet tweet);

    @DELETE("/api/tweets/{id}")
    Call<String> deleteTweet(@Path("id") Long id);

    @GET("/api/tweets")
    Call<List<Tweet>> getAllTweets();


    //------ Users API
    @POST("/api/users")
    Call<User> createUser(@Body User User);

    @GET("/api/users/{id}")
    Call<User> getUser(@Path("id") String id);

    @GET("/api/users")
    Call<List<User>> getAllUsers();
}