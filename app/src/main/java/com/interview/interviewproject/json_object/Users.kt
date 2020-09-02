package com.interview.interviewproject.json_object

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Users : Serializable {

    @SerializedName("blog")
    val blog :String? = null
    @SerializedName("location")
    val location :String? = null
    @SerializedName("bio")
    var bio :String? = ""
    @SerializedName("login")
    var login :String = ""
    @SerializedName("id")
    var id :Int = 0
    @SerializedName("node_id")
    var nodeId :String = ""
    @SerializedName("avatar_url")
    var avatarUrl :String = ""
    @SerializedName("gravatar_id")
    var gravatarId :String = ""
    @SerializedName("url")
    var url :String = ""
    @SerializedName("name")
    val name :String = ""
    @SerializedName("html_url")
    var htmlUrl :String = ""
    @SerializedName("followers_url")
    var followersUrl :String = ""
    @SerializedName("following_url")
    var followingUrl :String = ""
    @SerializedName("gists_url")
    var gistsUrl :String = ""
    @SerializedName("starred_url")
    var starredUrl :String = ""
    @SerializedName("subscriptions_url")
    var subscriptionsUrl :String = ""
    @SerializedName("organizations_url")
    var organizationsUrl :String = ""
    @SerializedName("repos_url")
    var reposUrl :String = ""
    @SerializedName("received_events_url")
    var receivedEventsUrl :String = ""
    @SerializedName("type")
    var type :String = ""
    @SerializedName("site_admin")
    var siteAdmin :String = ""
}