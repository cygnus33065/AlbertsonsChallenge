package com.example.acronyms.di

import com.example.acronyms.data.RetrofitInstance
import com.example.acronyms.data.AcronymService
import com.example.acronyms.data.repository.AcronymRepository
import com.example.acronyms.data.repository.IAcronymRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit() = RetrofitInstance().retrofit

    @Singleton
    @Provides
    fun providesAcronymService(retrofit: Retrofit) = retrofit.create(AcronymService::class.java)

    @Singleton
    @Provides
    fun providesAcronymRepository(acronymService: AcronymService): IAcronymRepository {
        return AcronymRepository(acronymService)
    }

}