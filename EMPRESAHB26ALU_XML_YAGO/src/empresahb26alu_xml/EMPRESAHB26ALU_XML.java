/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package empresahb26alu_xml;

import LOGICA.GestorEmpresaHB;
import POJOS.Departamento;

import java.text.ParseException;

public class EMPRESAHB26ALU_XML {

   public static void main(String[] args) throws ParseException {
      GestorEmpresaHB.comprobarConexion();
      GestorEmpresaHB.visualizarProxecto(1);
      GestorEmpresaHB.visualizarEmpregado("0010010");
      Departamento depa = GestorEmpresaHB.obtenerDepapartamento(3);
      System.out.println("\n" + depa);

      GestorEmpresaHB.anadirFuncionesPorDepartamento("Limpiar bancos", depa);

   }
}
