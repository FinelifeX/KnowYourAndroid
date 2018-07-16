package itis.kpfu.ru.knowyourandroid.repository

import android.support.annotation.NonNull

class RepositoryProvider {

    companion object {

        private var lessonRepository: LessonRepository = LessonRepository()
        private var testRepository: TestRepository = TestRepository()

        @NonNull
        fun getLessonRepository(): LessonRepository {
            if (lessonRepository == null) {
                lessonRepository = LessonRepository()
            }
            return lessonRepository
        }

        @NonNull
        fun getTestRepository(): TestRepository {
            if (testRepository == null) {
                testRepository = TestRepository()
            }
            return testRepository
        }
    }
}