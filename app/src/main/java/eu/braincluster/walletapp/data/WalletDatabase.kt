package eu.braincluster.walletapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wallet::class], version = 1)
abstract class WalletDatabase : RoomDatabase()
{
    abstract fun walletDao(): WalletDao

    companion object
    {
        private var instance: WalletDatabase? = null

        fun getInstance(context: Context): WalletDatabase
        {
            if (instance == null)
            {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    WalletDatabase::class.java,
                    "wallet.db"
                )
                    .build()
            }

            return instance!!
        }

        fun destroyInstance()
        {
            instance = null
        }
    }
}
