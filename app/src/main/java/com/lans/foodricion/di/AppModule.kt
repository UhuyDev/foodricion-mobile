package com.lans.foodricion.di

import android.content.Context
import com.lans.foodricion.common.Constant.BASE_URL
import com.lans.foodricion.data.repository.AuthRepository
import com.lans.foodricion.data.repository.ChatbotRepository
import com.lans.foodricion.data.repository.UserRepository
import com.lans.foodricion.data.source.local.DataStoreManager
import com.lans.foodricion.data.source.network.AuthAuthenticator
import com.lans.foodricion.data.source.network.AuthInterceptor
import com.lans.foodricion.data.source.network.api.FoodricionApi
import com.lans.foodricion.data.tensorflow.TfLiteClassifier
import com.lans.foodricion.domain.interactor.ForgotPasswordInteractor
import com.lans.foodricion.domain.interactor.GetChatbotHistoryInteractor
import com.lans.foodricion.domain.interactor.GetImageTempUriInteractor
import com.lans.foodricion.domain.interactor.GetMeInteractor
import com.lans.foodricion.domain.interactor.IsAuthenticatedInteractor
import com.lans.foodricion.domain.interactor.SendChatBotMessageInteractor
import com.lans.foodricion.domain.interactor.SignInInteractor
import com.lans.foodricion.domain.interactor.SignOutInteractor
import com.lans.foodricion.domain.interactor.SignUpInteractor
import com.lans.foodricion.domain.interactor.StoreSessionInteractor
import com.lans.foodricion.domain.interactor.VerifyOTPInteractor
import com.lans.foodricion.domain.interactor.validator.ValidateConfirmPasswordInteractor
import com.lans.foodricion.domain.interactor.validator.ValidateEmailInteractor
import com.lans.foodricion.domain.interactor.validator.ValidateFullnameInteractor
import com.lans.foodricion.domain.interactor.validator.ValidateOTPInteractor
import com.lans.foodricion.domain.interactor.validator.ValidatePasswordInteractor
import com.lans.foodricion.domain.interactor.validator.ValidatorInteractor
import com.lans.foodricion.domain.repository.IAuthRepository
import com.lans.foodricion.domain.repository.IChatbotRepository
import com.lans.foodricion.domain.repository.IUserRepository
import com.lans.foodricion.domain.tensorflow.FoodClassifier
import com.lans.foodricion.domain.usecase.ForgotPasswordUseCase
import com.lans.foodricion.domain.usecase.GetChatbotHistoryUseCase
import com.lans.foodricion.domain.usecase.GetImageTempUriUseCase
import com.lans.foodricion.domain.usecase.GetMeUseCase
import com.lans.foodricion.domain.usecase.IsAuthenticatedUseCase
import com.lans.foodricion.domain.usecase.SendChatbotMessageUseCase
import com.lans.foodricion.domain.usecase.SignInUseCase
import com.lans.foodricion.domain.usecase.SignOutUseCase
import com.lans.foodricion.domain.usecase.SignUpUseCase
import com.lans.foodricion.domain.usecase.StoreSessionUseCase
import com.lans.foodricion.domain.usecase.VerifyOTPUseCase
import com.lans.foodricion.domain.usecase.validator.ValidateConfirmPasswordUseCase
import com.lans.foodricion.domain.usecase.validator.ValidateEmailUseCase
import com.lans.foodricion.domain.usecase.validator.ValidateFullnameUseCase
import com.lans.foodricion.domain.usecase.validator.ValidateOTPUseCase
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(
        dataStoreManager: DataStoreManager
    ): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .authenticator(AuthAuthenticator(dataStoreManager))
            .addInterceptor(AuthInterceptor(dataStoreManager))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
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
    fun provideFoodClassifier(@ApplicationContext context: Context): FoodClassifier {
        return TfLiteClassifier(context)
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
    fun provideUserRepository(
        api: FoodricionApi
    ): IUserRepository {
        return UserRepository(api)
    }

    @Provides
    @Singleton
    fun provideChatbotRepository(
        api: FoodricionApi
    ): IChatbotRepository {
        return ChatbotRepository(api)
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
    fun provideSignOutUseCase(authRepository: IAuthRepository): SignOutUseCase {
        return SignOutInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetMeUseCase(userRepository: IUserRepository): GetMeUseCase {
        return GetMeInteractor(userRepository)
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
    fun provideForgotPasswordUseCase(userRepository: UserRepository): ForgotPasswordUseCase {
        return ForgotPasswordInteractor(userRepository)
    }

    @Provides
    @Singleton
    fun provideVerifyOTPUseCase(userRepository: UserRepository): VerifyOTPUseCase {
        return VerifyOTPInteractor(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetImageTempUriUseCase(@ApplicationContext context: Context): GetImageTempUriUseCase {
        return GetImageTempUriInteractor(context)
    }

    @Provides
    @Singleton
    fun provideSendChatbotMessageUseCase(chatbotRepository: ChatbotRepository): SendChatbotMessageUseCase {
        return SendChatBotMessageInteractor(chatbotRepository)
    }

    @Provides
    @Singleton
    fun provideGetChatbotHistoryUseCase(chatbotRepository: ChatbotRepository): GetChatbotHistoryUseCase {
        return GetChatbotHistoryInteractor(chatbotRepository)
    }

    @Provides
    @Singleton
    fun provideValidatorUseCase(
        validateEmailUseCase: ValidateEmailUseCase,
        validateUsernameUseCase: ValidateFullnameUseCase,
        validatePasswordUseCase: ValidatePasswordUseCase,
        validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
        validateOTPUseCase: ValidateOTPUseCase,
    ): ValidatorUseCase {
        return ValidatorInteractor(
            validateEmailUseCase,
            validateUsernameUseCase,
            validatePasswordUseCase,
            validateConfirmPasswordUseCase,
            validateOTPUseCase
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

    @Provides
    @Singleton
    fun provideValidateOTPUseCase(): ValidateOTPUseCase {
        return ValidateOTPInteractor()
    }
}