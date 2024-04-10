package com.saksham.jetpack.mapscompose.di

import android.app.Application
import androidx.room.Room
import com.saksham.jetpack.mapscompose.data.data_source.MapMarkerDatabase
import com.saksham.jetpack.mapscompose.data.repository.MapMarkerRepositoryImpl
import com.saksham.jetpack.mapscompose.domain.repository.MapMarkerRepository
import com.saksham.jetpack.mapscompose.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideMapsMarkerDatabase(application: Application): MapMarkerDatabase {
        return Room.databaseBuilder(application, MapMarkerDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideMapsMarkerRepository(db: MapMarkerDatabase): MapMarkerRepository {
        return MapMarkerRepositoryImpl(db.dao)
    }
}