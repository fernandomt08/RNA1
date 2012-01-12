public class Probar {
//Datos de entrenamiento
//Patrones de Calidad mala
static float[] entradas1 = {0.9f, 0.6f, 0.5f, 0.4f};
    static float[] entradas2 = {0.8f, 0.7f, 0.6f, 0.5f};
    static float[] entradas3 = {0.5f, 0.5f, 0.4f, 0.5f};
    static float[] entradas4 = {0.4f, 0.8f, 0.6f, 0.3f};
    static float[] entradas5 = {0.7f, 0.5f, 0.6f, 0.5f};
    static float[] entradas5a = {0.1f, 0.1f, .5f, 0.3f};

//Patrones de Calidad Buena
    static float[] entradas6 = {1.0f, 0.9f, 0.9f, 9.0f};
    static float[] entradas7 = {0.8f, 0.9f, 0.9f, 1.0f};
    static float[] entradas8 = {0.7f, 0.8f, 0.8f, 0.9f};
    static float[] entradas9 = {0.5f, 0.6f, 0.7f, 0.8f};
    static float[] entradas10 = {0.8f, 0.8f, 0.9f, 0.8f};
    static float[] entradas10a = {0.8f, 0.8f, 0.8f, 1.0f};

    static float[] salida1 = {-1.0f};
    static float[] salida2 = {1.0f};
        
//Datos para probar la red despues del entrenamiento
    static float[] prueba1 = {0.1f, 0.5f, 0.7f, 0.4f};
    static float[] prueba2 = {0.1f, 0.7f, 1.0f, 0.5f};
    static float[] prueba3 = {0.1f, 0.1f, 0.4f, 0.9f};
    static float[] prueba4 = {0.1f, 0.6f, 0.9f, 0.8f};
    static float[] prueba5 = {0.3f, 0.3f, 0.5f, 0.6f};
    static float[] prueba6 = {0.4f, 0.1f, 0.6f, 0.8f};
    static float[] prueba7 = {0.5f, 1.0f, 0.5f, 0.8f};
    static float[] prueba8 = {0.5f, 1.0f, 0.7f, 0.9f};
    static float[] prueba9 = {0.6f, 0.6f, 0.6f, 0.7f};
    static float[] prueba10 = {1.0f, 0.9f, 0.8f, 0.7f};
    static float[] prueba11 = {0.1f, 0.9f, 0.5f, 0.2f};
    static float[] prueba12 = {0.2f, 0.3f, 0.4f, 0.4f};
    static float[] prueba13 = {0.3f, 1.0f, 0.6f, 0.1f};
    static float[] prueba14 = {0.1f, 0.2f, 0.2f, 0.1f};
    static float[] prueba15 = {0.4f, 0.7f, 0.1f, 0.6f};
    static float[] prueba16 = {0.5f, 0.8f, 1.0f, 0.2f};
    static float[] prueba17 = {0.6f, 0.3f, 1.0f, 0.1f};
    static float[] prueba18 = {0.8f, 0.2f, 0.8f, 0.2f};
    static float[] prueba19 = {0.9f, 0.4f, 0.6f, 0.6f};
    static float[] prueba20 = {1.0f, 0.5f, 0.1f, 1.0f};
    

    public static void main(String[] args) {
    	//Creamos una nueva red neuronal
        RedNeuronal nn = new RedNeuronal(4, 4, 4, 1);
        //Agregamos los 3 datos de entrenamiento
        nn.agregarDatosEntrenamiento(entradas1, salida1);
        nn.agregarDatosEntrenamiento(entradas6, salida2);
        nn.agregarDatosEntrenamiento(entradas2, salida1);
        nn.agregarDatosEntrenamiento(entradas7, salida2);
        nn.agregarDatosEntrenamiento(entradas3, salida1);        
        nn.agregarDatosEntrenamiento(entradas8, salida2);
        nn.agregarDatosEntrenamiento(entradas4, salida1);
        nn.agregarDatosEntrenamiento(entradas9, salida2);
        nn.agregarDatosEntrenamiento(entradas5, salida1);
        nn.agregarDatosEntrenamiento(entradas10,salida2);

        double error = 0;
        //Realizamos el entrenamiento de la red 
        long tiempoInicio = System.currentTimeMillis();
	int iteraciones  = 900000, i = 0;
	float errorf = 1.0E-4f;
	
		 for ( i = 0;i < iteraciones; i++) {
         	       error = nn.entrenar();
		//System.out.println("E"+error);
		    if(error<errorf | i == iteraciones )
		        break;
		}
		
	System.out.println("\nError: "+errorf);
        System.out.println("\nIteraciones: "+iteraciones);
        System.out.println("\nCiclos: " + i + " Error: " + error+ " Porcentaje: "+error/100);

	System.out.println("Tiempo Total: " + (System.currentTimeMillis() - tiempoInicio)/1000.0 + "S");




        
        //Probamos la red entrenada con los datos de prueba
        Probar_configuracion(nn, prueba1);
        Probar_configuracion(nn, prueba2);
        Probar_configuracion(nn, prueba3);
        Probar_configuracion(nn, prueba4);/**/
  	Probar_configuracion(nn, prueba5);
        Probar_configuracion(nn, prueba6);
        Probar_configuracion(nn, prueba7);
        Probar_configuracion(nn, prueba8);
        Probar_configuracion(nn, prueba9);
        System.out.println("*********************************");
        Probar_configuracion(nn, prueba10);
        Probar_configuracion(nn, prueba11);
        Probar_configuracion(nn, prueba12);
        Probar_configuracion(nn, prueba13);
        Probar_configuracion(nn, prueba14);
        Probar_configuracion(nn, prueba15);
        Probar_configuracion(nn, prueba16);
        Probar_configuracion(nn, prueba17);
        Probar_configuracion(nn, prueba18);
        Probar_configuracion(nn, prueba19);
        Probar_configuracion(nn, prueba20);/**/
        nn.GuardarRed("test.rna");
        
        System.out.println("*******************************************************************");
        System.out.println("Cargando Datos...");
        RedNeuronal nn2 = RedNeuronal.CargarRed("Funciona.rna");
        Probar_configuracion(nn2, prueba1);
        Probar_configuracion(nn2, prueba2);
        Probar_configuracion(nn2, prueba3);
        Probar_configuracion(nn2, prueba4);/**/
  	Probar_configuracion(nn2, prueba5);
        Probar_configuracion(nn2, prueba6);
        Probar_configuracion(nn2, prueba7);
        Probar_configuracion(nn2, prueba8);
        Probar_configuracion(nn2, prueba9);
        System.out.println("*********************************");
        Probar_configuracion(nn2, prueba10);
        Probar_configuracion(nn2, prueba11);
        Probar_configuracion(nn2, prueba12);
        Probar_configuracion(nn2, prueba13);
        Probar_configuracion(nn2, prueba14);
        Probar_configuracion(nn2, prueba15);
        Probar_configuracion(nn2, prueba16);
        Probar_configuracion(nn2, prueba17);
        Probar_configuracion(nn2, prueba18);
        Probar_configuracion(nn2, prueba19);
        Probar_configuracion(nn2, prueba20);

       
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
