import java.util.*;
import java.io.*;

class RedNeuronal implements Serializable {
  private static final long serialVersionUID = 1L;  //Controlar la version de las modificaciones
  protected int numeroentradas;  //Número de neuronas en la capa de entrada
  protected int neuronascapa1; //Número de neuronaes en la primer capa oculta
  protected int neuronascapa2; //Número de neuronas en la segunda capa oculta
  protected int numerosalidas; //Número de neuronaes en la capa de salida

  protected int iteraciones;  //Número de iteraciones de entrenamiento

  public float entradas[];      //Almacena los datos de entrada
  protected float capaoculta1[];  //Almacena el valor de la funcion de activacion en la primera capa
  protected float capaoculta2[];  //Almacena el valor de la funcion de activacion  de cada neuronal en la segunda capa
  public float salidas[];     //Almacena los valores de cada neurona de la capa de salida

  protected float W1[][];    //Almacena los pesos de las conexiones entre la capa de entrada y la primer capa oculta
  protected float W2[][];    //Almacena los pesos de las conexiones entre la primer capa oculta y la segunda
  protected float W3[][];    //Almacena los pesos de las conexiones entre la segunda capa oculta y la capa de salida

  protected float error_salida[];  //Almacena el error que existe en cada una de las celulas de la capa de salida
  protected float capaoculta1_error[]; //Almacena el error que existe en cada celula de la primer capa
  protected float capaoculta2_error[]; //Almacena el error que existe en cada celula de la segunda capa oculta

  transient protected ArrayList entradasEntrenamiento = new ArrayList();  //Almacena los datos de entrenamiento(Datos de entrada)
  transient protected ArrayList salidasEntrenamiento = new ArrayList(); //Almacena los datos de entrenamiento(Resultados que se esperan obtener )

  public float FACTOR_APRENDIZAJE = 0.1f;   //Factor de aprendizaje
  private int ejemplo_actual = 0;
  public RedNeuronal(int numero_entradas, int num_capaoculta1, int num_capaoculta2, int numero_salidas) { //Constructor de la red neuronal
  							//Primero parametro: Número de neuronas en la capa de entrada
							//Segundo: Numero de neuronas en la primer capa oculta
 	                     				//Tercero: Numero de neuronas en la capa segunda oculta
 	                     				//Cuarto: Número de neuronas en la capa de salida
 	                     				
    numeroentradas = numero_entradas;	//Obtenemos el numero de neuronas que va a tener cada una de las capas de la neurona
    neuronascapa1 = num_capaoculta1;
    neuronascapa2 = num_capaoculta2;
    numerosalidas = numero_salidas;
    entradas = new float[numeroentradas];	//Inicializamos los arrays con el tamaño requerido
    capaoculta1 = new float[neuronascapa1];
    capaoculta2 = new float[neuronascapa2];
    salidas = new float[numerosalidas];
    W1 = new float[numeroentradas][neuronascapa1];  //Inicializamos las matrices que almacenan los pesos de las conexiones entre cada capa
    W2 = new float[neuronascapa1][neuronascapa2];
    W3 = new float[neuronascapa2][numerosalidas];
    
    generarPesos();		//Inicializa los pesos aleatoriamente

    error_salida = new float[numerosalidas];   //Inicializamos los vectores que almacenan los porcentajes de errores de la neurona
    capaoculta1_error = new float[neuronascapa1];
    capaoculta2_error = new float[neuronascapa2];
  }
  
  

  
	//Este metodo agrega los valores con los que se va a entrenar la red
  public void agregarDatosEntrenamiento(float[] entradas, float[] salidas) {
    if (entradas.length != numeroentradas || salidas.length != numerosalidas) {
      System.out.println("El numero de entradas y salidas no corresponde...");
      
    }
    else{
	    entradasEntrenamiento.add(entradas);  //almacena los datos de entrada
	    salidasEntrenamiento.add(salidas); //Almacena los datos que se esperan a la salida
    }
  }


//Método para iniciar aleatoriamente cada matriz de pesos(Con valores de -1 a +1)
  public void generarPesos() {

    for (int i = 0; i < numeroentradas; i++)
      for (int h = 0; h < neuronascapa1; h++)
        W1[i][h] = 2f * (float) Math.random() - 1f;
        
    for (int i = 0; i < neuronascapa1; i++)
      for (int h = 0; h < neuronascapa2; h++)
        W2[i][h] = 2f * (float) Math.random() - 1f;
        
    for (int h = 0; h < neuronascapa2; h++)
      for (int o = 0; o < numerosalidas; o++)
        W3[h][o] = 2f * (float) Math.random() - 1f;
  }



 
public float[] calcular(float[] ent) {
    for (int i = 0; i < numeroentradas; i++) 
    	entradas[i] = ent[i];
    	
    PropagaPesos();
    
    float[] ret = new float[numerosalidas];
    for (int i = 0; i < numerosalidas; i++) 
    	ret[i] = salidas[i];
    	
    return ret;
  }
  
  
//El metodo calcula los valores de salida en cada neurona, multiplica el valor del peso de la conexion por el valor de la funcion que
//toma la neurona con la que tiene conexion
  public void PropagaPesos() {
    int i, h, o;
    for (h = 0; h < neuronascapa1; h++) {
      capaoculta1[h] = 0.0f;
    }
    for (h = 0; h < neuronascapa2; h++) {
      capaoculta2[h] = 0.0f;
    }
    
    for (o = 0; o < numerosalidas; o++){
      salidas[o] = 0.0f;
    }
      
    
    //Primer Capa Oculta
    for (i = 0; i < numeroentradas; i++) {
      for (h = 0; h < neuronascapa1; h++) {
//	capaoculta1[h] += sigmoide(entradas[i] * W1[i][h]);
        capaoculta1[h] += entradas[i] * W1[i][h];// +1*1;  //1*1 es el Bias que agregue al algoritmo
      }

    }
    
      for (h = 0; h < neuronascapa1; h++) {
    	capaoculta1[h] += 1*1;//W1[numeroentradas][h];
      }
    
    //Segunda Capa oculta
    for (i = 0; i < neuronascapa1; i++) {
      for (h = 0; h < neuronascapa2; h++) {
        capaoculta2[h] += capaoculta1[i] * W2[i][h];
      }

    }
    
    for (h = 0; h < neuronascapa2; h++) {
    	capaoculta2[h] += 1*1;//Bias
      }

    //Capa de Salida     
      
    for (h = 0; h < neuronascapa2; h++) {
      for (o = 0; o < numerosalidas; o++) {
        salidas[o] += sigmoide(capaoculta2[h]) * W3[h][o];
      }

    }
    
    for (o = 0; o < numerosalidas; o++) {
      salidas[o] += 1*1;//Bias
      }
  }

  public float entrenar() {
    int i, h, o;
    float error = 0.0f;
    ArrayList entradasEntrenamiento = this.entradasEntrenamiento;
    ArrayList salidasEntrenamiento = this.salidasEntrenamiento;
    int num_casos = entradasEntrenamiento.size();


    for (h = 0; h < neuronascapa1; h++)
      capaoculta1_error[h] = 0.0f;
    for (h = 0; h < neuronascapa2; h++)
      capaoculta2_error[h] = 0.0f;
    for (o = 0; o < numerosalidas; o++)
      error_salida[o] = 0.0f;
      
      
    // Copia los valores de entrada
    for (i = 0; i < numeroentradas; i++) {
      entradas[i] = ((float[]) entradasEntrenamiento.get(ejemplo_actual))[i];
    }
    
    // Copia los valores de salida
    float[] salidaso = (float[]) salidasEntrenamiento.get(ejemplo_actual);
    



    PropagaPesos();
    
//Calcula error en cada neurona de las tres capas
    for (o = 0; o < numerosalidas; o++) {
      error_salida[o] = (salidaso[o] - salidas[o])* gradiente(salidas[o]);
//      System.out.println("Target: " + salidaso[o] + "Salida: " + salidas[o]);
    }
    for (h = 0; h < neuronascapa2; h++) {
      capaoculta2_error[h] = 0.0f;
      for (o = 0; o < numerosalidas; o++) {
        capaoculta2_error[h] += error_salida[o] * W3[h][o];
      }
    }
    for (h = 0; h < neuronascapa1; h++) {
      capaoculta1_error[h] = 0.0f;
      for (o = 0; o < neuronascapa2; o++) {
        capaoculta1_error[h] += capaoculta2_error[o] * W2[h][o];
      }
    }
    
    
    for (h = 0; h < neuronascapa2; h++) {
      capaoculta2_error[h] = capaoculta2_error[h] * gradiente(capaoculta2[h]);
    }
    for (h = 0; h < neuronascapa1; h++) {
      capaoculta1_error[h] = capaoculta1_error[h] * gradiente(capaoculta1[h]);
    }
      // Actualiza los pesos de las conexiones de acuerdo al error que existe
    // Actualiza pesos entre la capa 2 y la de salida
      for (o = 0; o < numerosalidas; o++) {
        for (h = 0; h < neuronascapa2; h++) {


          W3[h][o] += FACTOR_APRENDIZAJE * error_salida[o] * capaoculta2[h];
          W3[h][o] = normalizarPeso(W3[h][o]);

        }
      }
      //Actualiza los pesos entre la capa 1 y la capa 2
      for (o = 0; o < neuronascapa2; o++) {
        for (h = 0; h < neuronascapa1; h++) {
          W2[h][o] += FACTOR_APRENDIZAJE * capaoculta2_error[o] * capaoculta1[h];
          W2[h][o] = normalizarPeso(W2[h][o]);
        }
      }
      // Actualiza los pesos entre la capa de entrada y la primer capa oculta
      for (h = 0; h < neuronascapa1; h++) {
        for (i = 0; i < numeroentradas; i++) {
          W1[i][h] += FACTOR_APRENDIZAJE * capaoculta1_error[h] * entradas[i];
          W1[i][h] = normalizarPeso(W1[i][h]);
        }
      }
      for (o = 0; o < numerosalidas; o++) {
        error += Math.abs(salidaso[o] - salidas[o]);

      }
      ejemplo_actual++;
      if (ejemplo_actual >= num_casos) 
      	ejemplo_actual = 0;
      return error;
  }
//Normaliza valores de error(Entre -10 y +10)
  protected float normalizarPeso(float peso) {
    float w = peso;
    if (w < -10) 
    	w = -10;
    if (w > 10)  
    	w =  10;
    return w;
  }
  
  //Funcion sigmoidee
  protected float sigmoide(float x) {
    return (float) (1.0f / (1.0f + Math.exp((double) (-x))));   //C = 1
  }
//Primer derivada de la funcion sigmoide
  protected float gradiente(float x) {
    double z = sigmoide(x); //  + FACTOR_APRENDIZAJE;
    return (float) (z * (1.0f - z));
  }
//Este metodo obtiene de un archivo un objeto de tipo red neuronal
  public static RedNeuronal CargarRed(String nombre_archivo) {
    RedNeuronal rn = null;
    try {
      InputStream entradasEntrenamiento = ClassLoader.getSystemResourceAsStream(nombre_archivo);
      if (entradasEntrenamiento == null) {
        System.out.println("Error al tratar de abrir el archivo");
        System.exit(1);
      } else {
        ObjectInputStream p = new ObjectInputStream(entradasEntrenamiento);
        rn = (RedNeuronal) p.readObject();
        rn.entradasEntrenamiento = new ArrayList();
        rn.salidasEntrenamiento = new ArrayList();
        entradasEntrenamiento.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return rn;
  }

//Este metodo crea un archivo y almacena el estado del objeto. Almacena cada uno de los atributos de la red con sus valores.
  public void GuardarRed(String nombre_archivo) {
    try {
      FileOutputStream ostream = new FileOutputStream(nombre_archivo);
      ObjectOutputStream p = new ObjectOutputStream(ostream);
      p.writeObject(this);
      p.flush();
      ostream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
