package com.example.kotlin_demoapp.tagb.helper

class Constants {

    companion object {
        //Person API
        const val BASE_URL = "https://reqres.in/"

        // Movie API
        const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"
        const val MOVIE_IMAGE_BASE = "https://image.tmdb.org/t/p/original/"

        //Employee API
        const val EMPLOYEE_BASE_URL = "https://6488512c0e2469c038fd75e0.mockapi.io/api/"

        //Image Upload API
        const val IMAGE_BASE_URL = "https://api.upload.io/v2/accounts/FW25bQt/uploads/"

        //Authenticate User
        const val AUTH_BASE_URL = "https://staging-auth-api.runparking.com/api/v5/"

        const val MESSAGE = "message"
    }
}

class UserDefault {
    companion object {
        const val MOVIE_ACCESS_TOKEN =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMWVhZWRmNTM0MTk0YzJkOTZlMDEyZTIyYjJlOGQ3NyIsInN1YiI6IjY0ODgwYTM0NmY4ZDk1MDBlNTAwOGFjYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6s4osXoVK785xIHNMPDHMLX7j0ZMpOFPvyPezPU9Nrk"
        const val IMAGE_ACCESS_TOKEN = "Bearer public_FW25bQtDefwnNRqCHCT7v1ESEQTV"
    }
}

class Endpoints {
    companion object {
        const val LOGIN = "auth/user/login"
        const val SIGNUP = "auth/user/signup"
        const val GET_USER = "api/users?page=1"
        const val GET_MOVIES = "movie/popular"
        const val EMPLOYEE = "employee"
        const val EMPLOYEE_WITH_ID = "employee/{id}"
        const val FORM_DATA = "form_data"
    }
}

class RequestHeader {
    companion object {
        const val APP_VERSION = "appversion"
        const val PLATEFORM = "plateform"
    }
}

class HeaderValue {
    companion object {
        const val APP_VERSION = "1.0.3"
        const val PLATEFORM = "ios"
    }
}

class IntentKeys {
    companion object {
        const val FROM_NOTIFICATION = "from_notification"
    }
}

class RetrofitNames {
    companion object {
        const val USER = "UserRetrofit"
        const val EMPLOYEE = "EmployeeRetrofit"
        const val MOVIE = "MovieRetrofit"
        const val IMAGE = "ImageRetrofit"
        const val AUTH = "AuthenticationRetrofit"
    }
}