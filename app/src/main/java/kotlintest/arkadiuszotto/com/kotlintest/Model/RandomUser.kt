package kotlintest.arkadiuszotto.com.kotlintest.Model

import com.google.gson.annotations.SerializedName

/**
 * @author arekotto
 */
class RandomUser {

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("name")
    var name: Name? = null

    @SerializedName("location")
    var location: Location? = null

    inner class Name {

        @SerializedName("title")
        var title: String? = null

        @SerializedName("first")
        var first: String? = null

        @SerializedName("last")
        var last: String? = null
    }

    inner class Location {

        @SerializedName("street")
        var street: String? = null

        @SerializedName("city")
        var city: String? = null
    }
}