package eu.braincluster.walletapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Wallet::class), version = 1)
abstract class WalletDatabase : RoomDatabase()
{
    abstract fun walletDao(): WalletDao

    companion object
    {
        private var INSTANCE: WalletDatabase? = null

        fun getInstance(context: Context): WalletDatabase
        {
            if (INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    WalletDatabase::class.java, "wallet.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance()
        {
            INSTANCE = null
        }
    }
}