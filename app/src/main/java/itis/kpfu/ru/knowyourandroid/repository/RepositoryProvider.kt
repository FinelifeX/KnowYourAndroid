package itis.kpfu.ru.knowyourandroid.repository

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import itis.kpfu.ru.knowyourandroid.model.User
import itis.kpfu.ru.knowyourandroid.model.providers.UserProvider
import itis.kpfu.ru.knowyourandroid.repository.cache.Cache
import itis.kpfu.ru.knowyourandroid.ui.DrawerActivity

object RepositoryProvider {

    fun provideData(context: Context, auth: FirebaseAuth?) {
        Cache.clear()
        HandbookRepository.loadClasses {
            ThemeRepository.loadThemes {
                for (themeGroup in it) {
                    TestRepository.getTest(themeGroup.title)
                    LessonRepository.setLessons(themeGroup.items)
                }
                UserProvider.provideUser().addOnCompleteListener {
                    if (auth != null) {
                        googleLogin(auth)
                    }
                    DrawerActivity.start(context)
                }
            }
        }
    }

    fun googleLogin(auth: FirebaseAuth) {
        val user = UserProvider.getCurrentUser()
        if (user == null) {
            UserProvider.createUser(
                    User(auth.uid, auth.currentUser?.displayName,
                            avatarUrl = auth.currentUser?.photoUrl.toString()))
        }
    }
}