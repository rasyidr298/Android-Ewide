package com.ewide.test.rasyidr.di

import androidx.room.Room
import com.ewide.test.rasyidr.data.local.LocalDataSource
import com.ewide.test.rasyidr.data.local.room.AppDatabase
import com.ewide.test.rasyidr.data.remote.ApiService
import com.ewide.test.rasyidr.data.remote.RemoteDataSource
import com.ewide.test.rasyidr.ui.games.GamesViewModel
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val viewModelModule = module {
    viewModel { GamesViewModel(get(), get()) }
}

val databaseModule = module {
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("codext".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "Db.Room"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    factory { get<AppDatabase>().appDao() }
}

val networkModule = module {
    single {
        val timeOut = 60L

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .build()
    }

    single {
        fun appService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
        appService(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://www.cheapshark.com/api/1.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
}

val dataSourceModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
}