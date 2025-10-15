package com.example.proyectofinal.AsistenciaMecanica.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AsistenciaDBHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {


        // Tabla Persona
        db.execSQL("""
    CREATE TABLE persona (
        idP INTEGER PRIMARY KEY AUTOINCREMENT,
        nombre TEXT,
        edad INTEGER,
        numTel TEXT
    )
""")

        // Tabla Conductor
        db.execSQL("""
            CREATE TABLE conductor (
                idC INTEGER PRIMARY KEY AUTOINCREMENT,
                licencia TEXT,
                numAuto TEXT,
                fotoProb TEXT,
                idP INTEGER,
                FOREIGN KEY(idP) REFERENCES persona(idP)
            )
        """)

        // Tabla Mecanico
        db.execSQL("""
            CREATE TABLE mecanico (
                idM INTEGER PRIMARY KEY AUTOINCREMENT,
                especialidad TEXT,
                ubicacion TEXT,
                certificacion TEXT,
                fotoT TEXT,
                idP INTEGER,
                FOREIGN KEY(idP) REFERENCES persona(idP)
            )
        """)

        // Tabla Automovil
        db.execSQL("""
            CREATE TABLE automovil (
                placas TEXT PRIMARY KEY,
                modelo TEXT,
                marca TEXT,
                color TEXT,
                año TEXT,
                idC INTEGER,
                FOREIGN KEY(idC) REFERENCES conductor(idC)
            )
        """)


    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db!!?.execSQL("DROP TABLE IF EXISTS automovil")
        db.execSQL("DROP TABLE IF EXISTS mecanico")
        db.execSQL("DROP TABLE IF EXISTS conductor")
        db.execSQL("DROP TABLE IF EXISTS persona")
        onCreate(db)
    }
    companion object {
        private const val DATABASE_NAME = "asistenciam.db"
        private const val DATABASE_VERSION = 1
    }
}