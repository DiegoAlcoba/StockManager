PGDMP                       |            StockManager_BD    16.1    16.1 )    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16444    StockManager_BD    DATABASE     �   CREATE DATABASE "StockManager_BD" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
 !   DROP DATABASE "StockManager_BD";
                postgres    false            �            1259    16445    contabilidad    TABLE     �   CREATE TABLE public.contabilidad (
    operacionid integer NOT NULL,
    userid integer,
    fechainicio date NOT NULL,
    fechafin date NOT NULL,
    ingresos integer NOT NULL,
    gastos integer NOT NULL,
    balance integer NOT NULL
);
     DROP TABLE public.contabilidad;
       public         heap    postgres    false            �            1259    16448    contabilidad_operacionid_seq    SEQUENCE     �   CREATE SEQUENCE public.contabilidad_operacionid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.contabilidad_operacionid_seq;
       public          postgres    false    215            �           0    0    contabilidad_operacionid_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.contabilidad_operacionid_seq OWNED BY public.contabilidad.operacionid;
          public          postgres    false    216            �            1259    16494    detallepedido    TABLE     �   CREATE TABLE public.detallepedido (
    detalleid integer NOT NULL,
    idpedido integer,
    nombreproducto character varying(30),
    cantidad integer,
    costeunitario numeric(5,2)
);
 !   DROP TABLE public.detallepedido;
       public         heap    postgres    false            �            1259    16493    detallepedido_detalleid_seq    SEQUENCE     �   CREATE SEQUENCE public.detallepedido_detalleid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.detallepedido_detalleid_seq;
       public          postgres    false    224            �           0    0    detallepedido_detalleid_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.detallepedido_detalleid_seq OWNED BY public.detallepedido.detalleid;
          public          postgres    false    223            �            1259    16449    distribuidor    TABLE     �   CREATE TABLE public.distribuidor (
    distribid character varying(9) NOT NULL,
    nombredist character varying(30) NOT NULL,
    emaildist character varying(50) NOT NULL,
    disttelf integer NOT NULL
);
     DROP TABLE public.distribuidor;
       public         heap    postgres    false            �            1259    16452    pedido    TABLE     �   CREATE TABLE public.pedido (
    pedidoid integer NOT NULL,
    userid integer,
    fecha date NOT NULL,
    preciototal numeric(7,2) NOT NULL,
    distribid character varying(9)
);
    DROP TABLE public.pedido;
       public         heap    postgres    false            �            1259    16455    pedido_pedidoid_seq    SEQUENCE     �   CREATE SEQUENCE public.pedido_pedidoid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.pedido_pedidoid_seq;
       public          postgres    false    218            �           0    0    pedido_pedidoid_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.pedido_pedidoid_seq OWNED BY public.pedido.pedidoid;
          public          postgres    false    219            �            1259    16456    producto    TABLE     �   CREATE TABLE public.producto (
    nombreprod character varying(30) NOT NULL,
    distribid character varying(9),
    tipo character varying(30) NOT NULL,
    cantidad integer,
    costeunitario numeric(5,2) NOT NULL
);
    DROP TABLE public.producto;
       public         heap    postgres    false            �            1259    16459    usuario    TABLE     Q  CREATE TABLE public.usuario (
    userid integer NOT NULL,
    nombreusuario character varying(30) NOT NULL,
    contrasena character varying(30) NOT NULL,
    privilegios bit(1) NOT NULL,
    nombre character varying(30) NOT NULL,
    ssid integer NOT NULL,
    email character varying(50) NOT NULL,
    numtelefono integer NOT NULL
);
    DROP TABLE public.usuario;
       public         heap    postgres    false            �            1259    16462    usuario1_userid_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario1_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.usuario1_userid_seq;
       public          postgres    false    221            �           0    0    usuario1_userid_seq    SEQUENCE OWNED BY     J   ALTER SEQUENCE public.usuario1_userid_seq OWNED BY public.usuario.userid;
          public          postgres    false    222            1           2604    16463    contabilidad operacionid    DEFAULT     �   ALTER TABLE ONLY public.contabilidad ALTER COLUMN operacionid SET DEFAULT nextval('public.contabilidad_operacionid_seq'::regclass);
 G   ALTER TABLE public.contabilidad ALTER COLUMN operacionid DROP DEFAULT;
       public          postgres    false    216    215            4           2604    16497    detallepedido detalleid    DEFAULT     �   ALTER TABLE ONLY public.detallepedido ALTER COLUMN detalleid SET DEFAULT nextval('public.detallepedido_detalleid_seq'::regclass);
 F   ALTER TABLE public.detallepedido ALTER COLUMN detalleid DROP DEFAULT;
       public          postgres    false    223    224    224            2           2604    16464    pedido pedidoid    DEFAULT     r   ALTER TABLE ONLY public.pedido ALTER COLUMN pedidoid SET DEFAULT nextval('public.pedido_pedidoid_seq'::regclass);
 >   ALTER TABLE public.pedido ALTER COLUMN pedidoid DROP DEFAULT;
       public          postgres    false    219    218            3           2604    16465    usuario userid    DEFAULT     q   ALTER TABLE ONLY public.usuario ALTER COLUMN userid SET DEFAULT nextval('public.usuario1_userid_seq'::regclass);
 =   ALTER TABLE public.usuario ALTER COLUMN userid DROP DEFAULT;
       public          postgres    false    222    221            �          0    16445    contabilidad 
   TABLE DATA           m   COPY public.contabilidad (operacionid, userid, fechainicio, fechafin, ingresos, gastos, balance) FROM stdin;
    public          postgres    false    215   �0       �          0    16494    detallepedido 
   TABLE DATA           e   COPY public.detallepedido (detalleid, idpedido, nombreproducto, cantidad, costeunitario) FROM stdin;
    public          postgres    false    224   �0       �          0    16449    distribuidor 
   TABLE DATA           R   COPY public.distribuidor (distribid, nombredist, emaildist, disttelf) FROM stdin;
    public          postgres    false    217   �0       �          0    16452    pedido 
   TABLE DATA           Q   COPY public.pedido (pedidoid, userid, fecha, preciototal, distribid) FROM stdin;
    public          postgres    false    218   �0       �          0    16456    producto 
   TABLE DATA           X   COPY public.producto (nombreprod, distribid, tipo, cantidad, costeunitario) FROM stdin;
    public          postgres    false    220   1       �          0    16459    usuario 
   TABLE DATA           s   COPY public.usuario (userid, nombreusuario, contrasena, privilegios, nombre, ssid, email, numtelefono) FROM stdin;
    public          postgres    false    221   +1       �           0    0    contabilidad_operacionid_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.contabilidad_operacionid_seq', 1, false);
          public          postgres    false    216            �           0    0    detallepedido_detalleid_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.detallepedido_detalleid_seq', 1, false);
          public          postgres    false    223            �           0    0    pedido_pedidoid_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.pedido_pedidoid_seq', 1, false);
          public          postgres    false    219            �           0    0    usuario1_userid_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.usuario1_userid_seq', 1, true);
          public          postgres    false    222            6           2606    16467    contabilidad contabilidad_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.contabilidad
    ADD CONSTRAINT contabilidad_pkey PRIMARY KEY (operacionid);
 H   ALTER TABLE ONLY public.contabilidad DROP CONSTRAINT contabilidad_pkey;
       public            postgres    false    215            @           2606    16499     detallepedido detallepedido_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.detallepedido
    ADD CONSTRAINT detallepedido_pkey PRIMARY KEY (detalleid);
 J   ALTER TABLE ONLY public.detallepedido DROP CONSTRAINT detallepedido_pkey;
       public            postgres    false    224            8           2606    16469    distribuidor distribuidor_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.distribuidor
    ADD CONSTRAINT distribuidor_pkey PRIMARY KEY (distribid);
 H   ALTER TABLE ONLY public.distribuidor DROP CONSTRAINT distribuidor_pkey;
       public            postgres    false    217            :           2606    16471    pedido pedido_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (pedidoid);
 <   ALTER TABLE ONLY public.pedido DROP CONSTRAINT pedido_pkey;
       public            postgres    false    218            <           2606    16473    producto producto_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (nombreprod);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public            postgres    false    220            >           2606    16475    usuario usuario1_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario1_pkey PRIMARY KEY (userid);
 ?   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario1_pkey;
       public            postgres    false    221            C           2606    16500 )   detallepedido detallepedido_idpedido_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.detallepedido
    ADD CONSTRAINT detallepedido_idpedido_fkey FOREIGN KEY (idpedido) REFERENCES public.pedido(pedidoid);
 S   ALTER TABLE ONLY public.detallepedido DROP CONSTRAINT detallepedido_idpedido_fkey;
       public          postgres    false    218    4666    224            A           2606    16476    pedido pedido_distribid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_distribid_fkey FOREIGN KEY (distribid) REFERENCES public.distribuidor(distribid);
 F   ALTER TABLE ONLY public.pedido DROP CONSTRAINT pedido_distribid_fkey;
       public          postgres    false    218    4664    217            B           2606    16481     producto producto_distribid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_distribid_fkey FOREIGN KEY (distribid) REFERENCES public.distribuidor(distribid);
 J   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_distribid_fkey;
       public          postgres    false    220    217    4664            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   #   x�3�LL��̃��P� B;�I��bN�=... I�'     