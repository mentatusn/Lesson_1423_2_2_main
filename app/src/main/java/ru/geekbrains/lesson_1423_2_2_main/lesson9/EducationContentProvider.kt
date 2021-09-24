package ru.geekbrains.lesson_1423_2_2_main.lesson9

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import ru.geekbrains.lesson_1423_2_2_main.MyApp.Companion.getHistoryDAO
import ru.geekbrains.lesson_1423_2_2_main.R
import ru.geekbrains.lesson_1423_2_2_main.room.*


const val URI_ALL = 0
const val URI_ID = 1
const val ENTITY_PATH = "HistoryEntity"


class EducationContentProvider : ContentProvider() {


    private lateinit var uriMatcher: UriMatcher

    private var entityContentType: String? = null
    private var entityContentItemType: String? = null
    private lateinit var contentUri: Uri

    override fun onCreate(): Boolean {
        val authorities = context?.resources?.getString(R.string.authorities)
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        uriMatcher.addURI(authorities, ENTITY_PATH, URI_ALL)
        uriMatcher.addURI(authorities, "$ENTITY_PATH/#", URI_ID)

        entityContentType = "vnd.android.cursor.dir/vnd.$authorities.$ENTITY_PATH"
        // Тип содержимого — один объект
        entityContentItemType = "vnd.android.cursor.item/vnd.$authorities.$ENTITY_PATH"

        contentUri = Uri.parse("content://$authorities/$ENTITY_PATH")
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val historyDAO: HistoryDAO = getHistoryDAO()
        val cursor = when (uriMatcher.match(uri)) {
            URI_ALL -> {
                historyDAO.getHistoryCursor()
            }
            URI_ID -> {
                val id = ContentUris.parseId(uri)
                historyDAO.getHistoryCursor(id)
            }
            else -> throw IllegalStateException("это просто не возможно, кто ты воин?")
        }
        cursor.setNotificationUri(context?.contentResolver, contentUri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            URI_ALL -> {
                entityContentType
            }
            URI_ID -> {
                entityContentItemType
            }
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        require(uriMatcher.match(uri) == URI_ALL) { "NEED entityContentType, wrong uri $uri" }
        val historyDAO: HistoryDAO = getHistoryDAO()
        val entity = map(values)
        historyDAO.insert(entity)
        val resultUri = ContentUris.withAppendedId(contentUri,entity.id)
        context?.contentResolver?.notifyChange(resultUri,null)
        return resultUri
    }



    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        require(uriMatcher.match(uri) == URI_ALL) { "NEED entityContentType, wrong uri $uri" }
        val historyDAO: HistoryDAO = getHistoryDAO()
        val id = ContentUris.parseId(uri)
        historyDAO.deleteById(id)
        context?.contentResolver?.notifyChange(uri,null)
        return 1
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        require(uriMatcher.match(uri) == URI_ID) { "NEED entityContentItemType, wrong uri $uri" }
        val historyDAO: HistoryDAO = getHistoryDAO()
        val entity = map(values)
        historyDAO.update(entity)
        context?.contentResolver?.notifyChange(uri,null)
        return 1
    }


    private fun map(values: ContentValues?): HistoryEntity {
        return if (values == null) {
            HistoryEntity()
        } else {
            val id = if (values.containsKey(ID)) values[ID] as Long else 0
            val name = if (values.containsKey(NAME)) values[NAME] as String else ""
            val temperature = if (values.containsKey(TEMPERATURE)) values[TEMPERATURE] as Int else 0
            HistoryEntity(id,name,temperature)
        }
    }

}