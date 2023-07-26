package com.example.pruebaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class MainActivity : ComponentActivity() {

    val retrofit = RetroFitHelper.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        //Hay 3 tipos de hilos
        /*
        * MAIN = Hilo principal SOLO se usa para pintar la UI ya que bloquea lo demas hasta finalizar
        * IO = Hilo adecuado para peticiones que no requiera mucho procesamiento
        * Default = procesar informacion
        *
        * */

        //Crea una corrutina, lo que se ejecute dentro de este codigo sera dentro de la corrutina
        lifecycleScope.launch(Dispatchers.IO){

            //La peticion a la api se realiza con el hilo IO
           val response:Response<SuperHeroDataResponse> = retrofit.getSuperHeroes("a")

            //El toast, que es un elemento UI, se procesa con el hilo principal
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    Toast.makeText(this@MainActivity, "FUNCIONA", Toast.LENGTH_SHORT).show()
                }
            }

        }

        //Tira error por que no esta dentro de una corrutina
        //suscribete()
    }

    fun waitForCoroutines(){
        //Creo la corrutina
        lifecycleScope.launch(Dispatchers.IO){
            //Guardo cada peticion, con el async me aseguro de que hasta que no tenga la respuesta se pase a la siguiente accion
            val defferreds = listOf(
                async{ retrofit.getSuperHeroes("a")},
                async{ retrofit.getSuperHeroes("b")},
                async{ retrofit.getSuperHeroes("c")},
                async{ retrofit.getSuperHeroes("d")},
                async{ retrofit.getSuperHeroes("e")},
                async{ retrofit.getSuperHeroes("f")},
                )
            //Await a todas las peticiones que esten en la lista
            val response = defferreds.awaitAll()
        }

    }

    suspend fun suscribete(){
        //Las funciones suspend solo pueden ser llamadas dentro de corrutinas
    }
}

