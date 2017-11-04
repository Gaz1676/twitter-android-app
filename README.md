# &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;MyTweetApp

### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&emsp;&emsp;&emsp;&emsp;Main Features

    • Enables User Signup / Registration / Login
    • Enables user to post 140 character tweets
    • Tweets are persisted and will be reloaded when a User logs in
    • Supports viewing all tweets User has posted - the Users timeline
    • Allows a User to delete a subset of tweets
    • Allows a User to delete all tweets
    • Allows a user to edit account settings (email, password, and other details)
***
#### &emsp;&emsp;&emsp;Welcome Screen&emsp;&emsp;&emsp;&emsp;&nbsp;Sign Screen up&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Login Screen&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Tweet Screen

      • Single Activity      • Single Activity         • Single Activity      • Single Activity
      • Welcome message      • Four text views         • One button           • Three buttons
      • Two buttons:                1. First name      • Two text views              1. Tweet
            1. Sign up              2. Last name              1. Email               2. Contact
            2. Login:               3. Email                  2. Password            3. Email Tweet
                                    4. Password                                • Two text views:
                                                                                     1. "140 chars"
                                                                                     2. Date
                                                                               • Edit text view

  ***
#### MyTweet
_Entering text causes the number of characters to add up to 140_

_Pressing “Tweet” generates ‘Message Sent’ toast_

_Date is current date / time_

_Navigate back from MyTweet via “back”button or action bar backoption" to Timeline
***
#### Timeline
_Tweets appear in list in timeline_

_Selecting one brings up MyTweet activity with the text of the Tweet_

_Timeline has an action bar to navigate to MyTweet page_

_It also has a drop down menu which has:_

                            • Settings
                            • Clear all tweets
                            • Logout
***
#### MyTweet + Timeline (sample error handling)
_Ensures that empty tweets do not appear in timeline_

_Ensures tweet substring in timeline does not exceed single line_
***
#### Timeline Serialization
_Timeline is saved, so when app is launched, the tweets are displayed (if there are any)_
***
#### Timeline – Individual Tweet Deletion
_Long press on a tweet enables an individual tweet to be delete_
***
#### Contact List Access_
_Pressing “Contact” allows the user to select a contact from their contact list

_Their email is then displayed on the “Contact” button (Contact: homer@simpson.com)_
***
#### Email Access
_• Pressing “Send Tweet via Email” displays email application, which will contain email and tweet text_
***
#### Settings Support
_“Settings” menu option brings up the settings screen_

_The values entered will be saved and restored when the application is relaunched_
***
# &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;MyTweetApp Screenshots

<img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h400,w_200/v1509763866/splash_jraolg.jpg"/> <img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_400,w_200/v1507476493/login_es9uf9.png"/>

<img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_400,w_200/v1507476493/signup_iexta6.png"/> <img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_400,w_200/v1507476493/login_es9uf9.png"/>

<img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_400,w_200/v1509763866/timeline_hc9wef.png"/> <img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_400,w_200/v1509763866/tweet_zaghvn.png"/> <img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_400,w_200/v1509763866/settings_swdvh5.png"/>

