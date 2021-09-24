package ru.geekbrains.lesson_1423_2_2_main.lesson9

import android.Manifest
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentContentProviderBinding

class ContentProviderFragment : Fragment() {


    private var _binding: FragmentContentProviderBinding? = null
    private val binding: FragmentContentProviderBinding
        get() {
            return _binding!!
        }

    companion object {
        fun newInstance() = ContentProviderFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentProviderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun checkPermission() {
        context?.let {
            if (ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getContacts()
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                AlertDialog.Builder(it)
                    .setTitle("нужен доступ к контактам")
                    .setMessage("нужен доступ к контактам быстро!")
                    .setPositiveButton("выдать разрешение") { dialog, which ->
                        myRequestPermission()
                    }
                    .setNegativeButton("не выдам") { dialog, which ->
                        dialog.dismiss()
                    }
                    .create().show()
            } else {
                myRequestPermission()
            }
        }
    }

    private val REQUEST_CODE = 999

    private fun myRequestPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS),REQUEST_CODE)
        /*val launcher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    getContacts()
                } else {
                    //открываем окно плача
                }
            }

        launcher.launch(Manifest.permission.READ_CONTACTS)

        val launcherMulti =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->

                result.entries.forEach{
                    if (it.value) {
                        getContacts()
                    } else {
                        //открываем окно плача
                    }
                }
            }

        launcherMulti.launch(arrayOf(Manifest.permission.READ_CONTACTS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ))*/
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts()
                } else {
                    context?.let {
                        AlertDialog.Builder(it)
                            .setTitle("что-то новое")
                            .setMessage("что-то новое")
                            .setPositiveButton("что-то новое") { dialog, which ->
                                myRequestPermission()
                            }
                            .setNegativeButton("что-то новое") { dialog, which ->
                                dialog.dismiss()
                            }
                            .create().show()
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getContacts() {
        context?.let {
            val contentResolver: ContentResolver = it.contentResolver

            val cursor: Cursor? = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            cursor?.let { cursor ->
                for (i in 0 until cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        binding.containerForContacts.addView(TextView(it).apply {
                            text = name
                            textSize = 30f
                        })
                    }
                }
            }
        }
    }

}
