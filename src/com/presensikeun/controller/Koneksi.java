/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presensikeun.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Anjayani
 */

public class Koneksi {

	private static Connection koneksi;
     
public static Connection getKoneksi(){

	if(koneksi==null){
		try{
			koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_presensi", "root", "");
		} catch(SQLException ex) {
		    JOptionPane.showMessageDialog(null, "Database Tidak Terhubung" + ex.getMessage());
		}
	}

	return koneksi;

} static Object getConnection(){

        throw new UnsupportedOperationException("Tidak Dapat Terhubung");
	}

}
