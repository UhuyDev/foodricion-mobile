package com.lans.foodricion.di

import android.content.Context
import com.lans.foodricion.common.Constant.BASE_URL
import com.lans.foodricion.data.repository.AuthRepository
import com.lans.foodricion.data.source.local.DataStoreManager
import com.lans.foodricion.data.source.network.api.FoodricionApi
import com.lans.foodricion.domain.interactor.IsAuthenticatedInteractor
import com.lans.foodricion.domain.interactor.SignInInteractor
import com.lans.foodricion.domain.interactor.SignUpInteractor
import com.lans.foodricion.domain.interactor.StoreSessionInteractor
import com.lans.foodricion.domain.interactor.validator.ValidateConfirmPasswordInteractor
import com.lans.foodricion.domain.interactor.validator.ValidateEmailInteractor
import com.lans.foodricion.domain.interactor.validator.ValidateFullnameInteractor
import com.lans.foodricion.domain.interactor.validator.ValidatePasswordInteractor
import com.lans.foodricion.domain.interactor.validator.ValidatorInteractor
import com.lans.foodricion.domain.repository.IAuthRepository
import com.lans.foodricion.domain.usecase.IsAuthenticatedUseCase
import com.lans.foodricion.domain.usecase.SignInUseCase
import com.lans.foodricion.domain.usecase.SignUpUseCase
import com.lans.foodricion.domain.usecase.StoreSessionUseCase
import com.lans.foodricion.domain.usecase.validator.ValidateConfirmPasswordUseCase
import com.lans.foodricion.domain.usecase.validator.ValidateEmailUseCase
import com.lans.foodricion.domain.usecase.validator.ValidateFullnameUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatePasswordUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeInApi(client: OkHttpClient): FoodricionApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: FoodricionApi,
        dateStoreManager: DataStoreManager
    ): IAuthRepository {
        return AuthRepository(
            api,
            dateStoreManager
        )
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(authRepository: IAuthRepository): SignInUseCase {
        return SignInInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: IAuthRepository): SignUpUseCase {
        return SignUpInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun provideIsAuthenticatedUseCase(authRepository: IAuthRepository): IsAuthenticatedUseCase {
        return IsAuthenticatedInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun provideStoreSessionUseCase(authRepository: IAuthRepository): StoreSessionUseCase {
        return StoreSessionInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun provideValidatorUseCase(
        validateEmailUseCase: ValidateEmailUseCase,
        validateUsernameUseCase: ValidateFullnameUseCase,
        validatePasswordUseCase: ValidatePasswordUseCase,
        validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
    ): ValidatorUseCase {
        return ValidatorInteractor(
            validateEmailUseCase,
            validateUsernameUseCase,
            validatePasswordUseCase,
            validateConfirmPasswordUseCase
        )
    }

    @Provides
    @Singleton
    fun provideValidateEmailUseCase(): ValidateEmailUseCase {
        return ValidateEmailInteractor()
    }

    @Provides
    @Singleton
    fun provideValidateUsernameUseCase(): ValidateFullnameUseCase {
        return ValidateFullnameInteractor()
    }

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase(): ValidatePasswordUseCase {
        return ValidatePasswordInteractor()
    }

    @Provides
    @Singleton
    fun provideValidateConfirmPasswordUseCase(): ValidateConfirmPasswordUseCase {
        return ValidateConfirmPasswordInteractor()
    }
}