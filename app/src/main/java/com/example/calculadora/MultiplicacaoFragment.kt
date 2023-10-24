package com.example.calculadora

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.IllegalStateException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MultiplicacaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MultiplicacaoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var rootView: View
    private lateinit var botaoCalcular: Button
    /* -- ATT  27/09 --*/
    private lateinit var botaoMemoria: Button
    // ---------------- |
    private lateinit var valor1: EditText
    private lateinit var valor2: EditText
    private lateinit var calculoResultado: TextView

    private var calculo = 0
    /* -- ATT  27/09 --*/
    private var valorMemoria = 0
    //----------------- |

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_multiplicacao, container, false)

        botaoCalcular = findView(R.id.botao_calcular)
        valor1 = findView(R.id.primeiro_valor)
        valor2 = findView(R.id.segundo_valor)
        calculoResultado = findView(R.id.calculoResultado)

        /* -- ATT  27/09 --*/
        botaoMemoria = findView(R.id.memorizacao)
        //-----------------

        botaoCalcular.setOnClickListener{

            val numStr1 = valor1.text.toString()
            val numStr2 = valor2.text.toString()

            if(valor1.text.isNotEmpty()) {
                if(valor2.text.isNotEmpty()) {

                    val conv1 = numStr1.toInt()
                    val conv2 = numStr2.toInt()
                    calculo = conv1 * conv2

                    /* Aqui deve exibir o resultsado do calculo*/
                    var textoResultado = getString(R.string.texto_resultado)
                    calculoResultado.text = "$textoResultado = $calculo"
                }
            }
            valor1.setText(null)
            valor2.setText(null)
        }
        /* -- ATT  27/09 --*/
        botaoMemoria.setOnClickListener {
            valorMemoria = calculo
            valor1.setText(calculo.toString())
        }
        // ---------------- |
        return rootView
    }

    /* --- Função personalizada para encrontrar os ID's ----------- */
    private fun <T : View> findView(id: Int): T {
        if(!::rootView.isInitialized) {
            throw IllegalStateException("rootView não Inflada ainda.")
        }
        return rootView.findViewById(id)
    }
    /* ----------------------------------------------------------  */

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MultiplicacaoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MultiplicacaoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}