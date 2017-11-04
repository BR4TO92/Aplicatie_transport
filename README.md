# Aplicatie_transport

Problema:

Actorii principali din problema sunt urmatorii :
Userul : el interactioneaza cu aplicatia (in mod text sau grafic, cum preferi). El spune cat are de transportat si ce prioritate are la transport (are de ales
intre foarte urgent, urgent, normal, ieftin).
Brokerul de transport : el culege informatia de la user si apoi decide, pe baza unui algoritm care este cea mai potrivita posibilitate de transport (cu masina,
cu avionul, cu elicopterul, cu vaporul, cu trenul, cu tirul)

Regulile de transport sunt urmatoarele :
    1 palet : masina, avion, elicopter
    2-50 paleti : tren, tir
    >50 paleti : vapor
       
Viteza de transport e urmatoarea (la fel si costurile de transport, ordonate de la scump la ieftin) : elicopter, avion, masina, tir, tren, vapor.
Poti stabili care e pretul unui palet pt fiecare modalitate de transport.

La final aplicatia (brokerul) spune userului cat va costa transportul si cu ce va fi efectuat.
Nu sunt niste constrangeri fixe de care trebuie neaparat sa te tii in implementare. Vream sa vedem cum gandesti si ce cum implementezi.
Ar fi frumos sa folosesti un strategy pattern pentru, dar chiar fara e ok.

Implementare (observatii):
- toate computatiile pe baza unui singur User se vor face intr-o instanta a clasei TransportBroker
- mijlocul de transport se alege fie pe baza pretului cel mai scazut, fie vitezei cele mai ridicate
- pretul pentru un mijloc de transport a fost stabilit in functie de un pretul pe palet (cantitate) pentru fiecare vehicul in parte; (ulterior se poate calcula si in functie de o distanta data)
- tariful pentru prioritate a fost stabilit fix
- pentru fiecare client diferit am ales sa se creeze o instanta diferita, pentru a putea imbunatati ulterior programul pentru afisarea unui istoric de comenzi pentru fiecare client in parte;
- pentru o aplicatie grafica, propun o aplicatie JavaFX formata dintr-o fereastra din care se va introduce un nume si un pret (fiecare in cate un textBox), o prioritate (dintr-o lista tip comboBox) si o optiune de a alege cel mai rapid vehicul (checkbox). Un buton va calcula pretul si va afisa intr-o fereastra noua pretul si vehiculul folosit pentru transport.
