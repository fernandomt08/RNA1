public class Probar {
//Datos de entrenamiento
//Patrones de Calidad mala
    static float[] entradas1 = {0.9f, 0.4f, 0.3f, 0.4f};
    static float[] entradas2 = {0.8f, 0.7f, 0.6f, 0.5f};
    static float[] entradas3 = {0.5f, 0.5f, 0.4f, 0.5f};
    static float[] entradas4 = {0.4f, 0.8f, 0.6f, 0.3f};
    static float[] entradas5 = {0.3f, 0.5f, 0.7f, 0.2f};
    static float[] entradas5a = {0.1f, 0.1f, 1.0f, 0.3f};

//Patrones de Calidad Buena
    static float[] entradas6 = {1.0f, 0.9f, 0.9f, 1.0f};
    static float[] entradas7 = {0.8f, 0.9f, 0.9f, 1.0f};
    static float[] entradas8 = {0.7f, 0.8f, 0.8f, 0.9f};
//    static float[] entradas9 = {0.8f, 0.9f, 0.8f, 0.9f};
    static float[] entradas9 = {0.5f, 0.6f, 0.7f, 0.8f};
    static float[] entradas10 = {0.9f, 0.8f, 0.8f, 0.8f};
    static float[] entradas10a = {0.8f, 0.7f, 0.8f, 0.2f};
    

    static float[] salidas1 = {-1f};
    static float[] salidas2 = {-1f};
    static float[] salidas3 = {-1f};
    static float[] salidas4 = {-1f};
    static float[] salidas5 = {-1f};
    static float[] salidas5a = {-1f};        

    static float[] salidas6 = {1f};
    static float[] salidas7 = {1f};
    static float[] salidas8 = {1f};
    static float[] salidas9 = {1f};
    static float[] salidas10 = {1f};
    static float[] salidas10a = {1f};
        
//Datos para probar la red despues del entrenamiento
    static float[] prueba1 = {1.0f, 1.0f, 1.0f, 1.0f};
    static float[] prueba2 = {0.0f, 0.0f, 0.0f, 0.0f};
    static float[] prueba3 = {1.0f, 0.1f, 1.0f, 1.0f};

    public static void main(String[] args) {
    	//Creamos una nueva red neuronal
        RedNeuronal nn = new RedNeuronal(4, 4, 4, 1);
        //Agregamos los 3 datos de entrenamiento
        nn.agregarDatosEntrenamiento(entradas1, salidas1);
        nn.agregarDatosEntrenamiento(entradas6, salidas6);
        nn.agregarDatosEntrenamiento(entradas2, salidas2);
        nn.agregarDatosEntrenamiento(entradas7, salidas7);
        nn.agregarDatosEntrenamiento(entradas3, salidas3);
        nn.agregarDatosEntrenamiento(entradas8, salidas8);        
        nn.agregarDatosEntrenamiento(entradas4, salidas4);
        nn.agregarDatosEntrenamiento(entradas9, salidas9);
        nn.agregarDatosEntrenamiento(entradas5, salidas5);        
        nn.agregarDatosEntrenamiento(entradas10, salidas10);
        nn.agregarDatosEntrenamiento(entradas5a, salidas5a);
//        nn.agregarDatosEntrenamiento(entradas10a, salidas10a);

        double error = 0;
        //Realizamos el entrenamiento de la red //Hasta obtener 100,000 iteraciones
        double promedioe = 0d;
        long tiempoInicio = System.currentTimeMillis();
    /*    for(int ent = 0;ent<3;ent++){*/
		for (int i = 0;; i++) {
		    error = nn.entrenar();
		    if(error<1e-5 | i == 200000 ){
	        	System.out.println("Ciclos Totales: " + i + " Error= " + error/100);
	        	break;
	        	}
/*		    if (i > 0 && (i % 1000 == 0)) {
		        error /= 100;
		        
		        System.out.println("Ciclo " + i + " error= " + error);
		        
		        error = 0;
		    }*/
		}
//		System.out.println(" error= " + error/100.0 );
		System.out.println("Tiempo Total: " + (System.currentTimeMillis() - tiempoInicio)/1000.0 + "S");
/*		promedioe += error;
	}
	System.out.println(" errorp= " + promedioe/3);

  */     

        
        //Probamos la red entrenada con los datos de prueba
        Probar_configuracion(nn, prueba1);
        Probar_configuracion(nn, prueba2);
        Probar_configuracion(nn, prueba3);
	//Guardamos el objeto red neuronal
        nn.GuardarRed("test.rna");
        System.out.println("Cargando configuracion de red:");
        
        //Abre el archivo con el objeto almacenado anteriormente y probamos nuevamente para verificar que se guarda 
        //Correctamente la configuracion del objeto
        RedNeuronal nn2 = RedNeuronal.CargarRed("test.rna");

        Probar_configuracion(nn2, prueba1);
        Probar_configuracion(nn2, prueba2);
        Probar_configuracion(nn2, prueba3);
    }
//Impresiones de los datos de prueba y lo que se obtiene.
    public static void Probar_configuracion(RedNeuronal nn, float[] inputs) {
        float[] results = nn.calcular(inputs);
        System.out.print("Dato Prueba: ");
        for (int i = 0; i < inputs.length; i++)
        	System.out.print(inputs[i] + " ");
        	
        System.out.print(" Resultado: ");
        for (int i = 0; i < results.length; i++) 
        	System.out.print(results[i] + " \n");

    }

}
