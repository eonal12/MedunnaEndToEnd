package hooks;

import io.cucumber.java.Before;

import static base_urls.MedunnaBaseUrl.setUp;


public class Hooks {

    @Before("@api")//Bu tagi yazdığımızda sadece @api tag'ine sahip scenariolardan önce çalışır. Parantez içi boş bırakılırsa bu method tüm scenariolardan önce çalışır
                   //Peki biz neden her scenarioda çalışmasını istemiyoruz. Projeyi yormamak için. Mesela UI part'da ve database test'de spec'e ihtiyaç yoktur.
                    //Dolayısı ile gereksiz yere çalışıp token üretmesine gerek yok.
    public void beforeApi(){
        setUp();
    }
}
