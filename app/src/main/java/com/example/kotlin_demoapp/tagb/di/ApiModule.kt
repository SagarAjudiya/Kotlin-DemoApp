package com.example.kotlin_demoapp.tagb.di

import com.example.kotlin_demoapp.tagb.api_services.AuthenticationService
import com.example.kotlin_demoapp.tagb.api_services.EmployeeService
import com.example.kotlin_demoapp.tagb.api_services.ImageService
import com.example.kotlin_demoapp.tagb.api_services.MovieService
import com.example.kotlin_demoapp.tagb.api_services.UserService
import com.example.kotlin_demoapp.tagb.helper.Constants
import com.example.kotlin_demoapp.tagb.helper.RetrofitNames
import com.example.kotlin_demoapp.tagb.interceptors.RequestInterceptor
import com.example.kotlin_demoapp.tagb.module.authentication.repository.AuthenticationRepository
import com.example.kotlin_demoapp.tagb.module.dashboard.repository.EmployeeRepository
import com.example.kotlin_demoapp.tagb.module.dashboard.repository.ImageRepository
import com.example.kotlin_demoapp.tagb.module.dashboard.repository.MovieRepository
import com.example.kotlin_demoapp.tagb.module.dashboard.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    /**
     * User Module
     */
    @Provides
    @Singleton
    @Named(RetrofitNames.USER)
    fun getUserRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun getUserService(@Named(RetrofitNames.USER) retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun getUserRepository( service: UserService): UserRepository = UserRepository(service)

    /**
     * Movie Module
     */
    @Provides
    @Singleton
    @Named(RetrofitNames.MOVIE)
    fun getMovieRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun getMovieService(@Named(RetrofitNames.MOVIE) retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun getMovieRepository( service: MovieService): MovieRepository = MovieRepository(service)

    /**
     * Employee Module
     */
    @Provides
    @Singleton
    @Named(RetrofitNames.EMPLOYEE)
    fun getEmployeeRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.EMPLOYEE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().apply {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }.build()
            )
            .build()

    @Provides
    @Singleton
    fun getEmployeeService(@Named(RetrofitNames.EMPLOYEE) retrofit: Retrofit): EmployeeService = retrofit.create(EmployeeService::class.java)

    @Provides
    @Singleton
    fun getEmployeeRepository(employeeService: EmployeeService, imageService: ImageService): EmployeeRepository = EmployeeRepository(employeeService, imageService)

    /**
     * Image Module
     */
    @Provides
    @Singleton
    @Named(RetrofitNames.IMAGE)
    fun getImageRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.IMAGE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().apply {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }.build()
            )
            .build()

    @Provides
    @Singleton
    fun getImageService(@Named(RetrofitNames.IMAGE) retrofit: Retrofit): ImageService = retrofit.create(ImageService::class.java)

    @Provides
    @Singleton
    fun getImageRepository( service: ImageService): ImageRepository = ImageRepository(service)

    /**
     * Authentication Module
     */
    @Provides
    @Singleton
    @Named(RetrofitNames.AUTH)
    fun getAuthRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().apply {
                    connectTimeout(1, TimeUnit.MINUTES)
                    writeTimeout(1, TimeUnit.MINUTES)
                    readTimeout(1, TimeUnit.MINUTES)
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    addInterceptor(RequestInterceptor())
                }.build()
            )
            .build()

    @Provides
    @Singleton
    fun getAuthService(@Named(RetrofitNames.AUTH) retrofit: Retrofit): AuthenticationService = retrofit.create(AuthenticationService::class.java)

    @Provides
    @Singleton
    fun getAuthRepository( service: AuthenticationService): AuthenticationRepository = AuthenticationRepository(service)
}