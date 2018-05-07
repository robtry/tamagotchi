import java.util.Scanner;
class Encriptador {
  private String palabra;
  private int palabraCodepoint;
  private int[] letras;
  Encriptador() {
  }
  String encriptar(String palabra) {
    String encriptado = "";
    String temp;
    this.palabra = palabra;
    letras = new int[palabra.length()];
    palabraCodepoint = 0;
    //Le restamos a cada letra su codePoint para que se asignen las letras del abecedario del 0-25 respectivamente
    for(int i = 0; i < palabra.length(); i++) {
      letras[i] = palabra.codePointAt(i);
      //Sumamos todas las letras para formar el No. para desencriptar
      palabraCodepoint += letras[i];
    }
    //Encriptamos: Se resta al No. secreto cada letras y ese es su nuevo valor. Posteriormente sacamos su complemento a base 10 exponente 2
    for (int i = 0; i < letras.length; i++) {
      temp = Integer.toString(palabraCodepoint - letras[i]);
      encriptado = ((i != letras.length - 1) ? encriptado.concat(temp + " ") : encriptado.concat(temp));
    }
    return encriptado;
  }
  //Regresa el número secreto
  int darNumeroSecreto() {
    return palabraCodepoint;
  }
  String desencriptar(String encriptado, int noSecreto) {
    int temp;
    String desencriptado = "";
    int contador = 0;
    Scanner scan = new Scanner(encriptado);
    scan.useDelimiter(" ");
      //Desencripta: Saca complemento y le suma el noSecreto
      while(scan.hasNextInt()) {
        temp = noSecreto - scan.nextInt();
        char[] t = Character.toChars(temp);
        desencriptado += t[0];
      }
      return desencriptado;
    }
  }
