//Probar Red con algunos patrones
public class Probar {
//Datos de entrenamiento
    static float[] entradas1 = {0.3f, 0.5f, 0.9f};
    static float[] entradas2 = {0.2f, 0.3f, 0.1f};
    static float[] entradas3 = {0.8f, 0.4f, 0.3f};
    static float[] entradas4 = {0.8f, 0.8f, 0.8f};
    static float[] entradas5 = {0.5f, 0.6f, 0.7f};

    static float[] salidas1 = {1f};//, 0.1f, 0.1f};
    static float[] salidas2 = {-1f};//, 0.1f, 0.9f};
    static float[] salidas3 = {-1f};//, 0.9f, 0.1f};
    static float[] salidas4 = {1f};//, 0.9f, 0.1f};
    static float[] salidas5 = {1f};    
//Datos para probar la red despues del entrenamiento
    static float[] Prueba1 = {0.3f, 0.5f, 0.9f};
    static float[] Prueba2 = {0.8f, 0.8f, 0.8f};
    static float[] Prueba3 = {0.8f, 0.8f, 0.9f};
    
    static float[] Prueba4 = {0.2f, 0.3f, 0.1f};

    public static void main(String[] args) {
    	//Creamos una nueva red neuronal
        RedNeuronal nn = new RedNeuronal(3, 3, 3, 1);
        //Agregamos los 3 datos de entrenamiento
        nn.agregarDatosEntrenamiento(entradas1, salidas1);
        nn.agregarDatosEntrenamiento(entradas2, salidas2);
        nn.agregarDatosEntrenamiento(entradas3, salidas3);
        nn.agregarDatosEntrenamiento(entradas4, salidas4);
        nn.agregarDatosEntrenamiento(entradas4, salidas5);
        double error = 0;
        //Realizamos el entrenamiento de la red //Hasta obtener 100,000 iteraciones
        double promedioe = 0d;
    /*    for(int ent = 0;ent<3;ent++){*/
		for (int i = 0;; i++) {
		    error += nn.entrenar();
		    if(error<1e-3 | i == 300000 )
		        	break;
		    if (i > 0 && (i % 1000 == 0)) {
		        error /= 100;
		        
		        System.out.println("Ciclo " + i + " error= " + error);
		        
		        //error = 0;
		    }
		}
		System.out.println(" error= " + error);
/*		promedioe += error;
	}
	System.out.println(" errorp= " + promedioe/3);
  */     

        
        //Probamos la red entrenada con los datos de prueba
        Probar_configuracion(nn, Prueba1);
        Probar_configuracion(nn, Prueba2);
        Probar_configuracion(nn, Prueba3);
	//Guardamos el objeto red neuronal
        nn.GuardarRed("test.rna");
        System.out.println("Cargando configuracion de red:");
        
        //Abre el archivo con el objeto almacenado anteriormente y probamos nuevamente para verificar que se guarda 
        //Correctamente la configuracion del objeto
        RedNeuronal nn2 = RedNeuronal.CargarRed("test.rna");
       // nn2.agregarDatosEntrenamiento(entradas1, salidas1);
       // nn2.agregarDatosEntrenamiento(entradas2, salidas2);
        //nn2.agregarDatosEntrenamiento(entradas3, salidas3);
        //nn2.agregarDatosEntrenamiento(entradas4, salidas4);
        //nn2.agregarDatosEntrenamiento(entradas5, salidas5);
        Probar_configuracion(nn2, Prueba1);
        Probar_configuracion(nn2, Prueba2);
        Probar_configuracion(nn2, Prueba4);
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
	//Para dar formato a los datos
    public static String pp(float x) {
        String s = new String("" + x + "00");
        int index = s.indexOf(".");
        if (index > -1) s = s.substring(0, index + 3);
        if (s.startsWith("-") == false) s = " " + s;
        return s;
    }
}
