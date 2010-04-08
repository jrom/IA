package IA.Connectat;

import java.awt.*;
import java.util.Vector;
import java.util.HashMap;


/**
 <b>Grup:</b> 9.3<p>
 <b>Autor:</b> Marc Galceran Oms <p>
 <b>Data:</b> 16/11/2004<p>
 <dt><b>Descripcio:</b></dt>
 <dd> Un dibuix es un component que en un fons blanc
      dibuixa un grup de punts i fletxes entre aquests
      punts. <br>
      Cada punt s'identifica per un nombre enter en funcio
      de l'ordre en que ha estat entrat. Les fletxes son
      unions de dos punts. Aixo es fa aixi per aconseguir
      major eficiencia en comparacio a guardar totes les
      posicions en altres classes.<br>
`     Els eixos de coordenades tenen l'origen en el punt 
      de dalt a l'esquerre. Aixo vol dir que augmentar la 
      coordenada 'x' significa anar cap a la dreta i augmentar
      la coordenada 'y' significa anar cap avall. <br>
      A mes, es pot posar etiquetes als punts i es pot dibuixar
      una quadricula per a tenir mes referencies sobre les
      posicions dels punts. Tambe es poden dibuixar les fletxes
      de diferents colors per a poder marcar camins de fletxes.
 </dd><p>
 <dt><b>Versio:</b></dt>
 <dd>3 - arreglat un bug en el zoom<br>
         ja es pot dibuixar la quadricula<br>
         les fletxes ja no son totes del mateix color<br>
         els punts ja tenen etiquetes<br>
     2 - arreglat un bug al dibuixar punts<br>
         posat el tractament d'errors
 </dd>
*/
public class Dibuix extends Canvas {
	
    //ATRIBUTS DE LA CLASSE
        
    /** Radi dels punts dibuixats. De cara a enfora, el radi es un enter
      pero internament es un double per a tenir mes precisio al calcular
     les fletxes */  
    private double _radi;
    /** Contenidor amb la posicio horitzontal dels punts */
    private HashMap _posX;
    /** Contenidor amb la posicio vertical dels punts */
    private HashMap _posY;
    /** Contenidor amb les etiquetes dels punts */
    private HashMap _etiqPunt;
    /** Vector amb els punts d'origen de les fletxes */
    private Vector _puntsOrigen;
    /** Vector amb els punts de desti de les fletxes */	
    private Vector _puntsDesti;
    /** Indica el punt que esta seleccionat, -1 si no n'hi ha cap */
    private int _seleccionat; 
    /** Determina si es mostren les etiquetes */
    private boolean _mostrarEtiquetes;
    /** Determina la coordinada que correspon al limit esquerre del que
        es mostra per pantalla del dibuix */
    private int _esquerre;
    /** Determina la coordinada que correspon al limit dret del que
        es mostra per pantalla del dibuix. Ha de ser superior al limit
        esquerre */
    private int _dret;
    /** Determina la coordinada que correspon al limit superior del que
        es mostra per pantalla del dibuix */
    private int _superior;
    /** Determina la coordinada que correspon al limit inferior del que
        es mostra per pantalla del dibuix. El seu valor ha de ser superior
        al valor de _superior */  
    private int _inferior;
    /** Color amb que es pinten els punts */
    private Color _colorPunt;
    /** Color amb que es pinten les fletxes */
    private Color _colorFletxa;
    /** Color amb que es pinta el color seleccionat */
    private Color _colorSeleccionat;
    /** colors amb que es pinten els punts (cada punt es pot pintar d'un
        color diferent). */
    private HashMap _colorPunts;
    /** colors amb que es pinten les fletxes (cada fletxa es pot pintar d'un
        color diferent). */
    private Vector _colorFletxes;
    /** Si _dibuixarQuadricula es cert, es dibuixa una quadricula en el dibuix
    */
    private boolean _dibuixarQuadricula;
    /** Distancia entre 2 linies verticals de la quadricula */ 
    private int _tamanyQuadriculaX;
    /** Inici de les linies de la quadricula verticals */
    private int _iniciQuadriculaX;
    /** Distancia entre 2 linies horitzontals de la quadricula */
    private int _tamanyQuadriculaY;
    /** Inici de les linies de la quadricula horitzontals */
    private int _iniciQuadriculaY;
    /** Color de la quadricula */
    private Color _colorQuadricula;
    /** Diferencia entre coordenades minima per a evitar problemes si
      hi ha massa zoom */
    private static final int MINDIFERENCIA = 9;

    
    //CREADORES
	
    /** Constructora de la classe per defecte */
    public Dibuix () {
        /*//inicialitzar el component "Canvas" sobre el que dibuixem
        super ();
        //inicialitzar els vectors de punts i fletxes
        _posX = new HashMap();
        _posY = new HashMap();
        _etiqPunt = new HashMap();
        _puntsOrigen = new Vector();
        _puntsDesti = new Vector();
        //fons de color blanc
        setBackground (Color.white);
        //per defecte es mostren les etiquetes
        _mostrarEtiquetes = true;
        //inicialitzar les coordenades
        _esquerre=0;
        _dret=getSize().width; 
        if (_dret < MINDIFERENCIA) {
            _dret = MINDIFERENCIA+1;
        }
        _superior=0; 
        _inferior=getSize().height;
        if (_inferior < MINDIFERENCIA) {
            _inferior = MINDIFERENCIA+1;
        }
        //per defecte no hi ha cap punt seleccionat
        _seleccionat = -1;
        //calcular el radi dels punts del dibuix
        calcularRadi();
        //colors per defecte
        _colorPunt = Color.black;
        _colorFletxa = Color.black;
        _colorSeleccionat = Color.green;
        //al principi no es dibuixa cap quadricula
        _dibuixarQuadricula = false;
        _colorQuadricula = Color.black;
        _tamanyQuadriculaX = 50;
        _tamanyQuadriculaY = 50;*/
        super();
        this.init(getSize().width,getSize().height);
    }

    /** Constructora de la classe en que s'especifica el tamany que ha
      de tenir
      @param width amplada del dibuix
      @param height altura del dibuix 
    */
    public Dibuix (int width, int height) {
        //inicialitzar el component "Canvas" sobre el que dibuixem
        super ();
        this.init(width,height);
    }
    
    private void init(int width, int height){
        setSize (width, height);
        //inicialitzar els vectors de punts i fletxes
        _posX = new HashMap();
        _posY = new HashMap();
        _etiqPunt = new HashMap();
        _puntsOrigen = new Vector();
        _puntsDesti = new Vector();
        //fons de color blanc
        setBackground (Color.white);
        //per defecte es mostren les etiquetes
        _mostrarEtiquetes = true;
        //inicialitzar les coordenades
        _esquerre=0;
        _dret=width;
        if (_dret < MINDIFERENCIA) {
            _dret = MINDIFERENCIA+1;
        }
        _superior=0; 
        _inferior=height;
        if (_inferior < MINDIFERENCIA) {
            _inferior = MINDIFERENCIA+1;
        }
        //per defecte no hi ha cap punt seleccionat
        _seleccionat = -1;
        //calcular el radi dels punts del dibuix
        calcularRadi();
        //colors per defecte
        _colorPunt = Color.black;
        _colorFletxa = Color.black;
        _colorSeleccionat = Color.green;
        _colorFletxes = new Vector();
        _colorPunts = new HashMap();
        //al principi no es dibuixa cap quadricula
        _dibuixarQuadricula = false;
        _colorQuadricula = Color.black;
        _tamanyQuadriculaX = 50;
        _tamanyQuadriculaY = 50;
    }


    //CONSULTORES

    /** Retorna el radi amb que es dibuixen els punts. Externament el radi es
        veu com un enter, tot i que per millorar la precisio internament es
        un double
        @return radi dels punts del dibuix
    */
    public int getRadi () {
        return (int)_radi;
    }

    /** Retorna el color amb que es dibuixen els punts
        @return color dels punts
        @see java.awt.Color
    */
    public Color getColorPunt () {
        return _colorPunt;
    }

    /** Retorna el color amb que es dibuixa la fletxa que va del punt origen
        al punt desti.
        @param origen origen de la fletxa de que es vol saber el color
        @param desti desti de la fletxa de que es vol saber el color
        @return color de la fletxa indicada
        @throws Exception si la fletja no existeix
        @see java.awt.Color    
    */
    public Color getColorFletxa (int origen, int desti) throws Exception {
        boolean trobat = false;
        int i = 0;
        //recorregut per totes les fletxes
        while (i<_puntsOrigen.size() && !trobat) {
            int o = ((Integer)_puntsOrigen.elementAt(i)).intValue();
            int d = ((Integer)_puntsDesti.elementAt(i)).intValue();
            trobat = origen==o && desti==d;
            i++;
        }
        if (trobat) {
            i--;
            return (Color)_colorFletxes.elementAt(i);
        }
        else {
            throw new Exception ("No hi ha fletxa de " + origen + " " + desti);
        }
    }

    /** Retorna el color amb que es dibuixa el punt seleccionat.
        @return color del punt seleccionat
        @see java.awt.Color
    */
    public Color getColorSeleccionat () {
        return _colorSeleccionat;
    }

    /** Retorna si es mostren les etiquetes dels punts
        Encara que retorni cert, si el zoom no es suficient en el dibuix,
        es possible que les etiquetes no es dibuixin. Llavors s'ha d'apropar
        mes la imatge amb el zoom per veure les etiquetes.
        @return si es mostren les etiquetes dels punts
    */
    public boolean getMostrarEtiquetes () { 
        return _mostrarEtiquetes;
    }

    /** Retorna la posicio en l'eix de les x del punt id
        @param id punt del que es vol saber la posicio en l'eix de les x
        @return posicio del punt id en l'eix X
        @throws Exception si el punt id no existeix
    */
    public int getPosicioX (int id) throws Exception {
        int res = 0;
        if (_posX.containsKey(new Integer(id))) {
            res = ((Integer)_posX.get(new Integer(id))).intValue();
        } 
        else {
            throw new Exception ("El punt " + id + " no existeix");
        }
        return res;
    }


    /** Retorna la posicio en l'eix de les y del punt id
        @param id punt del que es vol saber la posicio en l'eix de les y
        @return posicio del punt id en l'eix Y
        @throws Exception si el punt id no existeix
    */
    public int getPosicioY (int id) throws Exception {
        int res = 0;
        if (_posY.containsKey(new Integer(id))) {
            res = ((Integer)_posY.get(new Integer(id))).intValue();
        } 
        else {
            throw new Exception ("El punt " + id + " no existeix");
        }
        return res;
    }

    /** Retorna el punt que esta seleccionat.
        @return el punt seleccionat. -1 si no hi ha cap punt seleccionat
    */
    public int getSeleccionat () {
        return _seleccionat;
    }

    /** Retorna el limit esquerre del dibuix.
        @return el punt en l'eix de les x corresponent al limit esquerre
                del dibuix
    */
    public int getLimitEsquerre () {
        return _esquerre;
    }

    /** Retorna el limit dret del dibuix.
        @return el punt en l'eix de les x corresponent al limit dret del dibuix
    */    
    public int getLimitDret () {
        return _dret;
    }

    /** Retorna el limit superior del dibuix.
        @return el punt en l'eix de les y corresponent al limit superior
                del dibuix
    */
    public int getLimitSuperior () {
        return _superior;
    }

    /** Retorna el limit inferior del dibuix.
        @return el punt en l'eix de les y corresponent al limit inferior del
                dibuix
    */
    public int getLimitInferior () {
        return _inferior;
    }

    /** Retorna si es dibuixa o no la quadricula en el dibuix
        @return si dibuixa la quadricula en el dibuix
    */
    public boolean getDibuixarQuadricula () {
        return _dibuixarQuadricula;
    }

    /** Retorna la distancia entre les linies verticals de la quadricula.
        @return la distancia entre linies verticals consecutives de la
                quadricula
    */
    public int getTamanyQuadriculaX () {
        return _tamanyQuadriculaX;
    }

    /** Retorna el desplacament inicial de les linies verticals.
        @return el desplacament inicial per si la quadricula no comenca al
                punt 0
    */
    public int getIniciQuadriculaX () {
        return _iniciQuadriculaX;
    }

    /** Retorna la distancia entre les linies horitzontals de la quadricula.
        @return la distancia entre linies horitzontals consecutives de la
                quadricula
    */
    public int getTamanyQuadriculaY () {
        return _tamanyQuadriculaY;
    }

    /** Retorna el desplacament inicial de les linies horitzontals.
        @return el desplacament inicial per si la quadricula no comenca al 
                punt 0
    */
    public int getIniciQuadriculaY () {
        return _iniciQuadriculaY;
    }

    /** Retorna el color amb que es dibuixa la quadricula.
        @return color de la quadricula
    */
    public Color getColorQuadricula () {
        return _colorQuadricula;
    }

    /** Retorna l'etiqueta del punt amb identificador id.
        @param id punt del que es vol saber l'etiqueta
        @return etiqueta del punt
        @throws Exception si el punt no existeix
    */
    public String getEtiquetaPunt (int id) throws Exception {
        String res = "";
        if (_etiqPunt.containsKey(new Integer(id))) {
            res = (String)_etiqPunt.get(new Integer(id));
        } 
        else {
            throw new Exception ("El punt " + id + " no existeix");
        }
        return res;
    }

    /** Si hi ha un punt dibuixat en la coordenada (x,y), retorna el seu
        identificador (un natural). Si hi ha mes d'un punt retorna el 
        d'identificar menor. Sino retorna -1. 
        En sistemes de coordinades amb molta diferencia entre amplada i
        altura de les coordenades, pot donar alguns problemes en els contorns
        del punt, ja que esta intentant reconeixer un punt quan conceptualment
        hi ha dibuixat un oval que es veu com un punt degut a la deformacio
        del sistema de coordenades. En qualsevol cas, el centre del punt 
        sempre el reconeix.
        @param x coordenada x en que es vol saber si hi ha un punt
        @param y coordenada y en que es vol saber si hi ha un punt
        @return punt que hi ha en la coordenada (x,y)
    */
    public int existeixPunt (int x, int y) {
        boolean trobat = false;
        int res = -1;
        double radix, radiy;
        /* si la imatge no te coordenades quadrades, al dibuixar-se el punt
           rodo, al mirar el diametre per les coordenades no sera el mateix
           el diametre horitzontal que el vertical
        */
        if (_dret-_esquerre<_inferior-_superior) {
            radix = _radi;    
            radiy = _radi * (_inferior-_superior) / (_dret-_esquerre);  
        }
        else {
            radix = _radi * (_dret-_esquerre) / (_inferior-_superior);
            radiy = _radi;
        }
        Object[] key = _posX.keySet().toArray();
        int i;
        for (int k = _posX.size()-1; k>=0&&!trobat; k--) {
            //calcul de la distancia entre el punt entrat i el punt i
            i = ((Integer)key[k]).intValue();
            int x1 = ((Integer)_posX.get(new Integer(i))).intValue();
            x1 -= x;
            x1 *= x1;
            int y1 = ((Integer)_posY.get(new Integer(i))).intValue();
            y1 -= y;
            y1 *= y1;
            if (x1+y1<radix*radiy) {
                //el punt entrat es troba dins el punt i
                res = i;
                trobat = true;
            }
        }
	  //retornem el punt que hem trobat o -1
        return res;
    }

    /** Retorna cert si hi ha una fletxa entre el punt p1 i el punt p2
        @param origen punt del que hauria de sortir la fletxa
        @param desti punt al que hauria d'arribar la fletxa
        @return si existeix la fletxa que va del punt origen al desti
    */
    public boolean existeixFletxa (int origen, int desti) {
        boolean trobat = false;
        //recorregut per totes les fletxes
        for (int i = 0; i<_puntsOrigen.size() && !trobat; i++) {
            int o = ((Integer)_puntsOrigen.elementAt(i)).intValue();
            int d = ((Integer)_puntsDesti.elementAt(i)).intValue();
            trobat = origen==o && desti==d;
        }
        return trobat;
    }


    //MODIFICADORES

    /** Canvia el tamany del dibuix reestablint el sistema de 
        coordenades
        @param width amplada del dibuix
        @param height altura del dibuix
    */
    public void setSize (int width,int height) {
        //establir tamany del dibuix
        super.setSize (width, height);
        //reestablir les coordenades originals
        _dret = width;
        _inferior = height;
        _esquerre = 0;
        _superior = 0;
        /*si el tamany del component es massa petit, posem les coordenades
          minimes */
        if (_dret < MINDIFERENCIA) {
            _dret = MINDIFERENCIA+1;
        }
        if (_inferior < MINDIFERENCIA) {
            _inferior = MINDIFERENCIA+1;
        }
        //calcular el radi dels punts del dibuix
        calcularRadi();
	//repintar el dibuix
        repaint();
    }

    /** Estableix el radi dels punts
        @param r nou radi dels punts
        @throws Exception si r es menor o igual a 0 o es mes gran que l'amplada
                          o altura del dibuix
    */
    public void setRadi (int r) throws Exception {
        if (r>0 && r<(_dret-_esquerre) && r<(_inferior-_superior)) {
            _radi = r;
            repaint();
        }
        else {
            throw new Exception ("Radi amb valor invalid " + r );
        }
    }

    /** Estableix el color amb que es pinten els punts
        @param c Color dels punts
        @see java.awt.Color
    */
    public void setColorPunts (Color c) {
        _colorPunt = c;
        repaint();
    }
    
    /** Estableix el color amb que es pinten les fletxes
        @param c Color de les fletxes
        @see java.awt.Color
    */
    public void setColorFletxes (Color c) {
        _colorFletxa = c;
        repaint();
    }
    
    /* estableix el color d'un punt */
    public void setColorPunt (int id, Color c) 
                               throws Exception {
        if (!_colorPunts.containsKey(new Integer(id))) {
            throw new Exception ("El punt no existeix");
        }
        _colorPunts.put (new Integer(id), c);
        repaint();
    }

    /** Estableix el color amb que es pinten les fletxes. Si hi ha fletxes
        bidireccionals de diferents colors, el tronc de la fletxa es veura del
        color que fa menys temps que ha estat modificat.
        @param origen punt d'origen de la fletxa de que es vol canviar el color
        @param desti punt de desti de la fletxa de que es vol canviar el color
        @param c nou color de la fletxa que surt d'origen i va a desti
        @throws Exception si la fletxa que va d'origen a desti no existeix
    */
    public void setColorFletxa (int origen, int desti, Color c) 
                               throws Exception {
        boolean trobat = false;
        int lloc = 0;
        int i = 0;
        //indica si la fletxa desti->origen existeix despres en el vector
        boolean posterior=false;
        int post = 0;
        //recorregut per totes les fletxes
        while (i<_puntsOrigen.size()) {
            int o = ((Integer)_puntsOrigen.elementAt(i)).intValue();
            int d = ((Integer)_puntsDesti.elementAt(i)).intValue();
            if (origen==o && desti==d) {
                trobat = true;
                lloc = i;
            }
            if (origen==d && desti==o && trobat) {
                post = i;
                posterior = true;
            }
            i++;
        }
        if (trobat) {
            if (posterior) {
                /* intercanviem l'ordre de les fletxes perque el nou color
                   sigui el que es vegi */
                _puntsOrigen.set(lloc,new Integer(desti));
                _puntsOrigen.set(post,new Integer(origen));
                _puntsDesti.set(lloc,new Integer(origen));
                _puntsDesti.set(post,new Integer(desti));
                Color aux = (Color)_colorFletxes.elementAt(post);
                _colorFletxes.set(lloc,aux);
                lloc = post;
            }
            _colorFletxes.set(lloc,c);
            repaint();
        }
        else {
            throw new Exception ("No hi ha fletxa de " + origen + " a " + desti);
        }
    }

    /** Estableix el color amb que es pinta el punt seleccionat
        @param c nou color del punt seleccionat
    */
    public void setColorSeleccionat (Color c) {
        _colorSeleccionat = c;
        repaint();
    }

    /** Estableix quin es el punt seleccionat
        @param id punt a seleccionar
    */
    public void setSeleccionat (int id) {
        _seleccionat = id;
        repaint();
    }

    /** Fa que no hi hagi cap punt seleccionat
    */
    public void deseleccionar () {
        _seleccionat = -1;
        repaint();
    }

    /** Determina si s'han de mostrar o no les etiquetes dels punts.
        Tot i que es posi que s'han de mostrar, si el dibuix no s'apropa
        suficientment als punts les etiquetes no es mostraran, per intentar
        evitar solapaments entre punts i etiquetes i fletxes.
        @param b determina si es dibuixaran les etiquetes dels punts
    */
    public void setMostrarEtiquetes (boolean b) {
        _mostrarEtiquetes = b;
        repaint();
    }

    /** Decideix si es dibuixa o no quadricula en el dibuix. En cas que s'hagi
        de dibuixar, comprova que els parametres son correctes i dibuixa
        @param b determina si es dibuixa o no quadricula
        @throws Exception si el tamany de la quadricula es massa petit
    */
    public void setDibuixarQuadricula (boolean b) throws Exception {
        if (b&&!_dibuixarQuadricula) {
            //no es dibuixava i ara es dibuixa
            _dibuixarQuadricula = b;
            //comprovem que tenim les linies amb una separacio minima de 5
            if (_tamanyQuadriculaX < 5 || _tamanyQuadriculaY < 5) {
                throw new Exception ("Parametres de la quadricula incorrectes");
            }
            else {
                repaint();
            }
        }
        else if (!b&&_dibuixarQuadricula) {
            //es dibuixava la quadricula i ara no es dibuixa
            _dibuixarQuadricula = b;
            //actualitzem el dibuix
            repaint();        
        }
    }

    /** Estableix la distancia entre les linies verticals de la quadricula
        @param tamany distancia entre 2 linies verticals de la quadricula
        @throws Exception si el tamany es menor que 5
    */
    public void setTamanyQuadriculaX (int tamany) throws Exception {
        if (tamany < 5) {
            throw new Exception ("Distancia entre linies massa petita");
        }
        else {
            _tamanyQuadriculaX = tamany;
            if (_dibuixarQuadricula && _tamanyQuadriculaY >= 5) {
                repaint();
            }
        }
    }

    /** Estableix el desplacament inicial de les linies verticals
        @param desp inici en que es comenca a dibuixar les linies verticals
    */
    public void setIniciQuadriculaX (int desp) {
        _iniciQuadriculaX = desp;
        if (_dibuixarQuadricula) {
            if (_tamanyQuadriculaX >= 5 || _tamanyQuadriculaY >= 5) {
                repaint();
            }
        }        
    }

    /** Estableix la distancia entre les linies horitzontals de la quadricula
        @param tamany distancia entre 2 linies horitzontals de la quadricula
        @throws Exception si el tamany es menor que 5
    */
    public void setTamanyQuadriculaY (int tamany) throws Exception {
        if (tamany < 5) {
            throw new Exception ("Distancia entre linies massa petita");
        }
        else {
            _tamanyQuadriculaY = tamany;
            if (_dibuixarQuadricula && _tamanyQuadriculaX >= 5) {
                repaint();
            }
        }
    }

    /** Estableix el desplacament inicial de les linies horitzontals
        @param desp inici en que es comenca a dibuixar les linies horitzontals
    */
    public void setIniciQuadriculaY (int desp) throws Exception {
        _iniciQuadriculaY = desp;
        if (_dibuixarQuadricula) {
            if (_tamanyQuadriculaX >= 5 || _tamanyQuadriculaY >= 5) {
                repaint();
            }
        }        
    }

    /** Estableix el color de la quadricula
        @param c color de la quadricula
    */
    public void setColorQuadricula (Color c) {
        _colorQuadricula = c;
        repaint();
    }

    /** Afegeix un nou punt en el dibuix en el lloc (x,y) amb etiqueta buida
        Retorna l'enter que identifica aquest punt dintre el dibuix
        @param x punt en l'eix de les x en que es posa el punt
        @param y punt en l'eix de les y en que es posa el punt
        @return nombre que identifica el punt
        @throws Exception si no s'aconsegueix crear el punt bé
    */
    public int dibuixarPunt (int x, int y) throws Exception {
        return dibuixarPunt(x,y,new String(""));
        /*int i = 0;
        while (_posX.containsKey(new Integer(i))) {
            i++;
        }
        //i es una clau buida
        _posX.put(new Integer(i),new Integer(x));
        try {
            _posY.put(new Integer(i),new Integer(y));
        }
        catch (Exception e) {
            //si no hem pogut entrar la segona coordenada, esborrem la primera
            _posX.remove(new Integer(i));
            throw e;
        }
        //afegim una etiqueta buida al punt
        try {
            _etiqPunt.put(new Integer(i),new String(""));
        }
        catch (Exception e) {
            //si no podem entrar l'etiqueta, esborrem el fet fins ara
            _posX.remove (new Integer(i));
            _posY.remove (new Integer(i));
            throw e;
        }
        repaint();
        //retorna l'identificador intern del punt
        return i;*/
    }

    /** Afegeix un nou punt en el dibuix en el lloc (x,y) amb etiqueta s
        Retorna l'enter que identifica aquest punt dintre el dibuix
        @param x punt en l'eix de les x en que es posa el punt
        @param y punt en l'eix de les y en que es posa el punt
        @param s etiqueta del punt
        @return nombre que identifica el punt
        @throws Exception si no s'aconsegueix crear el punt bé
    */    
    public int dibuixarPunt (int x, int y, String s) throws Exception {
        int i = 0;
        while (_posX.containsKey(new Integer(i))) {
            i++;
        }
        //i es una clau buida
        _posX.put(new Integer(i),new Integer(x));
        try {
            _posY.put(new Integer(i),new Integer(y));
        }
        catch (Exception e) {
            //si no hem pogut entrar la segona coordenada, esborrem la primera
            _posX.remove(new Integer(i));
            throw e;
        }
        //afegim una etiqueta buida al punt
        try {
            _etiqPunt.put(new Integer(i),s);
        }
        catch (Exception e) {
            //si no podem entrar l'etiqueta, esborrem el fet fins ara
            _posX.remove (new Integer(i));
            _posY.remove (new Integer(i));
            throw e;
        }
        // afegim el color del punt
        try {
            _colorPunts.put(new Integer(i),_colorPunt);
        }
        catch (Exception e) {
	        // Si no hem pogut afegir el color esborrem el fet fins ara
            _posX.remove (new Integer(i));
            _posY.remove (new Integer(i));
            _etiqPunt.remove (new Integer(i));
            throw e;
        }
        repaint();
        //retorna l'identificador intern del punt
        return i;
    }

    /** Estableix l'etiqueta del punt amb identificador id
        @param id punt a canviar l'etiqueta
        @param s nova etiqueta del punt
        @throws Exception si id no es un punt
    */
    public void setEtiquetaPunt (int id, String s) throws Exception {
        if (_etiqPunt.containsKey(new Integer(id))) {
            _etiqPunt.put(new Integer(id),s);
        }
        else {
            throw new Exception ("El punt " + id + " no existeix");
        }
        repaint();
    }

    /** Mou el punt amb identificador id a la posicio (x,y)
        @param x punt en l'eix de les x a on es mou el punt
        @param y punt en l'eix de les y a on es mou el punt
        @param id punt a moure
        @throws Exception si el punt id no existeix o no es pot moure el punt
    */ 
    public void mourePunt (int id, int x, int y) throws Exception{
        if (!_posX.containsKey(new Integer(id))) {
            throw new Exception ("El punt no existeix");
        }
        int aux = ((Integer)_posX.get(new Integer(id))).intValue();
        _posX.put (new Integer(id), new Integer(x));
        try {
            _posY.put (new Integer(id), new Integer(y));
        }
        catch (Exception e) {
            /*si hi ha hagut un error a l'entrar la segona dada, hem de
              restaurar la primera */
            _posX.put (new Integer(id), new Integer(aux));
            //tornem a llencar l'excepcio perque l'agafi algun nivell superior
            throw e;
        }
        repaint();
    }

    /** Suprimeix el punt amb l'identificador id
        @param id punt a suprimir
    */
    public void suprimirPunt (int id) {
        //suprimir les fletxes en que participava id
        for (int i = 0; i<_puntsOrigen.size();) {
            int o = ((Integer)_puntsOrigen.elementAt(i)).intValue();
            int d = ((Integer)_puntsDesti.elementAt(i)).intValue();
            if (o==id||d==id) {
                _puntsOrigen.removeElementAt(i);
                _puntsDesti.removeElementAt(i);
                _colorFletxes.removeElementAt(i);
            }
            else {
                i++;
            }
        }
        if (_seleccionat==id) {
		    _seleccionat=-1;
        }
        _posX.remove (new Integer(id));
        _posY.remove (new Integer(id));
        _colorPunts.remove (new Integer(id));
        _etiqPunt.remove (new Integer(id));
        repaint();
    }

    /** Afegeix una nova fletxa entre els punts origen i desti.
        No es permeten fletxes entre el mateix punt ni repetides 
        @param origen punt d'origen de la fletxa
        @param desti punt de desti de la fletxa
        @throws Exception si la fletxa ja existeix o no es pot crear
    */	
    public void dibuixarFletxa (int origen, int desti) throws Exception {
        boolean trobat = false;
        if (!_posX.containsKey(new Integer(origen))||
            !_posY.containsKey(new Integer(desti))) {
            throw new Exception ("Punts de la fletxa incorrectes");
        }
        for (int i = 0; i<_puntsOrigen.size() && !trobat; i++) {
            int o = ((Integer)_puntsOrigen.elementAt(i)).intValue();
            int d = ((Integer)_puntsDesti.elementAt(i)).intValue();
            trobat = o==origen&&d==desti;
        }
        if (!trobat && origen!=desti) {
            _puntsOrigen.addElement (new Integer (origen));
            try {
                _puntsDesti.addElement (new Integer (desti));
            }
            catch (Exception e) {
                /*si no s'ha pogut afegir el punt de desti, esborrem el
                  d'origen per mantenir la coherencia entre els vectors */
                _puntsOrigen.removeElementAt (_puntsOrigen.size()-1);
                throw e;
            }
            try {
                //per defecte posem color negre
                _colorFletxes.addElement (_colorFletxa);
            }
            catch (Exception e) {
                /* si no s'ha pogut afegir l'element, esborrem tot el que s'ha
                   tocat fins ara */
                _puntsOrigen.removeElementAt (_puntsOrigen.size()-1);
                _puntsDesti.removeElementAt (_puntsDesti.size()-1);
                throw e;
            }
            repaint();
        }
        else {
            //es posa en un string perque no cap en una sola linia
            String s = "La fletxa ja existeix o te el";
            s += " mateix origen i desti";
            throw new Exception (s);
        }
    }	

    /** Suprimeix la fletxa entre origen i desti
        @param p1 punt d'origen de la fletxa
        @param p2 punt de desti de la fletxa
        @throws Exception si la fletxa a suprimir no existeix
    */
    public void suprimirFletxa (int p1, int p2) throws Exception {
        boolean esborrat = false;
        for (int i = 0; i<_puntsOrigen.size() && !esborrat; i++) {
            int o = ((Integer)_puntsOrigen.elementAt(i)).intValue();
            int d = ((Integer)_puntsDesti.elementAt(i)).intValue();
            if ((esborrat = o==p1&&d==p2)) {
                _puntsOrigen.removeElementAt(i);
                _puntsDesti.removeElementAt(i);
                _colorFletxes.removeElementAt(i);
            }
        }
        if (!esborrat) {
            throw new Exception ("No hi ha fletxa de " + p1 + " a " + p2);
        }
        repaint();
    }
    
    /** Esborra tots els punts i fletxes
    */
    public void esborrarTot () {
        _posX.clear();
        _posY.clear();
        _etiqPunt.clear();
        _colorPunts.clear();
        _colorFletxes.removeAllElements();
        _puntsOrigen.removeAllElements();
        _puntsDesti.removeAllElements();
        _seleccionat = -1;
        _dibuixarQuadricula = false;
        repaint();
    }
    public void esborrarFletxes () {
        _colorFletxes.removeAllElements();
        _puntsOrigen.removeAllElements();
        _puntsDesti.removeAllElements();
        repaint();
    }

    /** Enquadra les coordenades d'acord amb els parametres.
        Posa el limit superior esquerre en (x1,y1) i l'inferior
        dret en (x2,y2). Només ho fa en cas que no inverteixin la
        imatge (x2>x1 i y2>y1) i la distancia entre punts sigui com
        a minim 10.
        @param x1 limit esquerre del dibuix
        @param y1 limit superior del dibuix
        @param x2 limit dret del dibuix
        @param y2 limit inferior del dibuix
        @throws Exception si els limits no son coherents
    */
    public void enquadrar (int x1, int y1, int x2, int y2) throws Exception {
        if (x2>x1+MINDIFERENCIA&&y2>y1+MINDIFERENCIA) {
            _esquerre = x1;
            _dret = x2;
            _superior = y1;
            _inferior = y2;
            repaint();
        }
        else {
            /* les coordenades no son correctes. Si no es controles aquest
               error el dibuix podria sortir invertit o del reves, i es vol
               evitar aquest fet. */
            throw new Exception ("Les coordenades entrades no son correctes");
        }
    }
    
    /** Canvia el sistema de coordenades ampliant la imatge el
        percentatge que indica el parametre rebut 
        @param percentatge percentatge en que s'augmenta la imatge
        @throws Exception si la diferencia entre limits es massa petita
    */
    public void zoom (int percentatge) throws Exception {
        boolean correcte = true;
        int rang_x = _dret-_esquerre;
        int rang_y = _inferior-_superior;
        _esquerre += rang_x*(((double)percentatge)/100);
        _dret -= rang_x*(((double)percentatge)/100);
        //si hi ha massa zoom, no permetem augmentar mes el zoom
        if (_dret <= _esquerre + MINDIFERENCIA) {
            _dret = _esquerre + MINDIFERENCIA + 1;
            correcte = false;
        }
        _superior += rang_y*(((double)percentatge)/100);
        _inferior -= rang_y*(((double)percentatge)/100);
        if (_inferior <= _superior + MINDIFERENCIA) {
            _inferior = _superior + MINDIFERENCIA + 1;
            correcte = false;
        }
        repaint();
        if (!correcte) {
            /*tot i que no es cap error, es llanca una excepcio per indicar que
              mes zoom no modificara la imatge */
            throw new Exception ("S'ha arribat al maxim zoom permes");
        }
    }

    /** Mou el sistema de coordenades tantes unitats com indiquen els
        parametres
        @param x distancia horitzontal a moure els limits del dibuix
        @param y distancia vertical a moure els limits del dibuix
    */
    public void moure (int x, int y) {
        _esquerre += x;
        _superior += y;
        _dret += x;
        _inferior += y;
        repaint();
    }

    
    //ALTRES FUNCIONS
    
    /** Algorisme que dibuixa tots els punts i fletxes
        @see java.awt.Graphics
    */
    public void paint (Graphics g) {
        if (_dibuixarQuadricula) {
            dibuixarQuadricula (g);
        }
        dibuixarPunts (g);
        dibuixarFletxes (g);
    }


    //PRIVADES

    /** Dibuixa els punts del Dibuix
        @see java.awt.Graphics
    */
    private void dibuixarPunts (Graphics g) {
        int x1, y1;
        String s;
        int rang_x = getSize().width;
        int rang_y = getSize().height;
        int iradi;
        //es pasa el radi del sistema de coordenades intern al del Dibuix
        if ((_dret-_esquerre)<(_inferior-_superior)) {
            iradi = (int)(_radi*rang_x/(_dret-_esquerre));
        }
        else {
            iradi = (int)(_radi*rang_y/(_inferior-_superior));
        }
        //si la relacio es massa petita, es posa radi 1
        if (iradi == 0) {
            iradi = 1;
        }
        g.setColor (_colorPunt);
        //dibuixar punt per punt
        Object[] key = _posX.keySet().toArray();
        int i;
        for (int k = 0; k<_posX.size(); k++) {
            //calcul de la distancia entre el punt entrat i el punt i
            i = ((Integer)key[k]).intValue();
            g.setColor ((Color)_colorPunts.get(new Integer(i)));
            if (i == _seleccionat) {
                g.setColor (_colorSeleccionat);
            }
            x1 = ((Integer)_posX.get(new Integer(i))).intValue()-_esquerre;
            y1 = ((Integer)_posY.get(new Integer(i))).intValue()-_superior;
            //adequacio al sistema de coordenades extern del dibuix
            x1 = x1*rang_x/(_dret-_esquerre)-iradi;
            y1 = y1*rang_y/(_inferior-_superior)-iradi;
            //dibuixem el punt
            g.fillOval (x1,y1,iradi*2,iradi*2);
            s = (String)_etiqPunt.get(new Integer(i));
            /* nomes dibuixem l'etiqueta si estem prou aprop, per intentar 
               evitar solapaments entre lletres i punts. Es podrien utilitzar
               algorismes per a evitar tots els solapaments, pero per
               eficiencia (seria molt farragos en cas de molts punts) millor
               no tocar-ho ja que ja hi ha un boolea per si no es vol mostrar
               els punts  */
            if (s.length()<2*iradi && _mostrarEtiquetes) {
                g.drawString(s,x1+iradi,y1);
            }
            /*if (i == _seleccionat) {
                g.setColor (_colorPunt);
            }*/
        }
    }
    
    /** Dibuixa les fletxes del dibuix
        @see java.awt.Graphics
    */
    private void dibuixarFletxes (Graphics g) {
        int x1,y1,x2,y2;
        int o,d;
        int rang_x = getSize().width;
        int rang_y = getSize().height;
        double dist,angle;
        double radiaux;
        //punts per a dibuixar la fletxa (una fletxa es un triangle)
        //adequacio del radi al sistema de coordenades exterior del dibuix
        if ((_dret-_esquerre)<(_inferior-_superior)) {
            radiaux = (int)(_radi*rang_x/(_dret-_esquerre));
        }
        else {
            radiaux = (int)(_radi*rang_y/(_inferior-_superior));
        }
        if (radiaux<1) {
            radiaux = 1;
        }
        for (int i = 0; i<_puntsOrigen.size(); i++) {
            g.setColor ((Color)_colorFletxes.elementAt(i));
            /*cerca de les coordenades dels punts de la fletxa adequant les
              coordenades */
            o = ((Integer)_puntsOrigen.elementAt(i)).intValue();
            x1 = ((Integer)_posX.get(new Integer(o))).intValue()-_esquerre;
            x1 = x1*rang_x/(_dret-_esquerre);
            y1 = ((Integer)_posY.get(new Integer(o))).intValue()-_superior;
            y1 = y1*rang_y/(_inferior-_superior);
            d = ((Integer)_puntsDesti.elementAt(i)).intValue();
            x2 = ((Integer)_posX.get(new Integer(d))).intValue()-_esquerre;
            x2 = x2*rang_x/(_dret-_esquerre);
            y2 = ((Integer)_posY.get(new Integer(d))).intValue()-_superior;
            y2 = y2*rang_y/(_inferior-_superior);
            //calcul del punt exacte de l'exterior del cercle on s'ha d'anar
            dist = Math.sqrt ((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
            angle = Math.asin ((y2-y1)/dist);
            y1 += (int)(radiaux*Math.sin(angle)); 
            y2 -= (int)(radiaux*Math.sin(angle));
            if (x2>x1) {
                x1 += (int)(radiaux*Math.cos(angle)); 
                x2 -= (int)(radiaux*Math.cos(angle));
            }
            else {
                x1 -= (int)(radiaux*Math.cos(angle));
                x2 += (int)(radiaux*Math.cos(angle));      
            } 
            //dibuix del tronc de la fletxa
            g.drawLine (x1,y1,x2,y2);
            g.drawLine (x1+1,y1+1,x2+1,y2+1);
	      /*dibuixar fletxa, crear un triangle que apunti
              a un extrem del punt de la direccio d'on ve la fletxa */
            /*pol_y[0]=y2;
            pol_x[0]=x2;
            pol_y[1]=pol_y[0]-
                     2*(int)(radiaux/2*Math.sin(angle-Math.PI/4)); 
            pol_y[2]=pol_y[0]-
                     2*(int)(radiaux/2*Math.cos(angle-Math.PI/4));
            if (x2>x1) {
                pol_x[1]=pol_x[0]-
                         2*(int)(radiaux/2*Math.cos(angle-Math.PI/4));
                
                pol_x[2]=pol_x[0]+
                         2*(int)(radiaux/2*Math.sin(angle-Math.PI/4)); 
            }
            else {
                pol_x[1]=pol_x[0]+
                         2*(int)(radiaux/2*Math.cos(angle-Math.PI/4));
                pol_x[2]=pol_x[0]-
                         2*(int)(radiaux/2*Math.sin(angle-Math.PI/4)); 
            }
            //dibuixar la fletxa
            g.fillPolygon (pol_x,pol_y,3);*/
        }
    }

    /** Dibuixa la quadricula
        @see java.awt.Graphics
    */
    private void dibuixarQuadricula (Graphics g) {
        int rang_x = getSize().width;
        int rang_y = getSize().height;
        int aux;
        g.setColor(_colorQuadricula);
        int i = (_iniciQuadriculaX-_esquerre)
                   % _tamanyQuadriculaX;
        /*des del principi de la part del dibuix que es mostra, anar fent
          una linia vertical cada _tamanyQuadriculaX. S'ha de traduir les 
          coordenades de (_esquerre a _dreta) a (0 a width) */
        aux = i * rang_x / (_dret-_esquerre);
        while (aux<rang_x) {
            g.drawLine (aux,rang_y,aux,0);
            i += _tamanyQuadriculaX;
            aux = i * rang_x / (_dret-_esquerre);
        }
        //igual pero amb linies horitzontals
        i = (_iniciQuadriculaY-_superior) % _tamanyQuadriculaY;
        aux = i * rang_y / (_inferior-_superior);
        while (aux<rang_y) {
            g.drawLine (0,aux,rang_x,aux);
            i += _tamanyQuadriculaY;
            aux = i * rang_y / (_inferior-_superior);
        }
    }

    /** Calcula el valor del radi dels punts
    */
    private void calcularRadi() {
        if ((_dret-_esquerre)<(_inferior-_superior)) {
            _radi = (_dret-_esquerre)/60;
        }
        else {
            _radi = (_inferior-_superior)/60;
        }
        if (_radi == 0) {
            _radi = 1;
        }
    }	
}
