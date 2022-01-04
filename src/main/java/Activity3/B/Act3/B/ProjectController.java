/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity3.B.Act3.B;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class ProjectController {
    
    @RequestMapping("/input")
    public String getData(HttpServletRequest data, Model kasir){
        
        String Nama = data.getParameter("nama_barang");
        String Jumlah = data.getParameter("jumlah_barang");
        String Harga = data.getParameter("harga_barang");
        String Uang = data.getParameter("bayar_awal");
        String Keterangan = data.getParameter("ket");
        String diskon = "";
      
        Double Quantity = Double.valueOf(Jumlah);
        Double Price = Double.valueOf(Harga);
        Double Money = Double.valueOf(Uang);
        Double Total = Price * Quantity;
        Double getTotal = null;
      
        if (Total < 16000)
        {
          getTotal = Total - (0 * Total/100);
          diskon = "0%";
        }
       else if (Total >= 16000 && Total < 25000)
        {
           getTotal = Total - (10 * Total/100);
           diskon = "10%";
        }
        else if (Total >= 25000)
        {
           getTotal = Total - (15 * Total/100);
           diskon = "15%";
        }
        
        Double discount = Total * getTotal;
        Double Change = Money - getTotal;
        Double Kurang = Total - Money;
        
        if (Total > Money)
        {
            Keterangan = "Uang anda kurang Rp." + Kurang;
        }
        
        else if(Total < Money)
        {
            Keterangan = "Kembalian anda Rp." + Change;   
        }
        kasir.addAttribute("nama_barang", Nama);
        kasir.addAttribute("harga_barang", Total);
        kasir.addAttribute("jumlah_barang", Jumlah);
        kasir.addAttribute("harga_diskon", discount);
        kasir.addAttribute("bayar_awal", Uang);
        kasir.addAttribute("diskon_a",diskon);
        kasir.addAttribute("total", getTotal);
        kasir.addAttribute("kembalian", Change);
        kasir.addAttribute("Ket", Keterangan);
        return "projectviewer";
    }
}
