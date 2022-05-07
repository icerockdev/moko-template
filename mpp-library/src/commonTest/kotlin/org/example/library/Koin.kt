package org.example.library

import com.russhwolf.settings.Settings
import io.ktor.client.engine.mock.*
import org.example.library.feature.list.newsResponseMock
import org.koin.core.context.startKoin
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module


fun startTestKoin(settings: Settings) = startKoin {
    startLibraryKoin(
        // test module
        module {
            single { settings }
            single { TestAntilog() }
            single(StringQualifier("BaseUrl")) {
                "https://localhost"
            }
            single {
                MockEngine { request ->
                    if (request.url.encodedPath == "top-headlines") {
                        respondOk(newsResponseMock)
                    } else {
                        respondBadRequest()
                    }
                }
            }
            single {
                TestUnitFactory()
            }
        }
    )
}
