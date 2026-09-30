import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<AppDatabase> {
        val builder = getDatabaseBuilder(ctx = get())
        getDb(builder)
    }
}