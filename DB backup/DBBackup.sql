PGDMP     '    "        
        x           parallel_tbd0    12.2    12.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    46708    parallel_tbd0    DATABASE     �   CREATE DATABASE parallel_tbd0 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
    DROP DATABASE parallel_tbd0;
                postgres    false            �            1259    46716 	   habilidad    TABLE     b   CREATE TABLE public.habilidad (
    id bigint NOT NULL,
    descrip character varying NOT NULL
);
    DROP TABLE public.habilidad;
       public         heap    postgres    false            �            1259    46724    vol_habilidad    TABLE     v   CREATE TABLE public.vol_habilidad (
    id bigint NOT NULL,
    id_vol bigint NOT NULL,
    id_hab bigint NOT NULL
);
 !   DROP TABLE public.vol_habilidad;
       public         heap    postgres    false            �            1259    46711 
   voluntario    TABLE     �   CREATE TABLE public.voluntario (
    id bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    fnacimiento date NOT NULL
);
    DROP TABLE public.voluntario;
       public         heap    postgres    false                      0    46716 	   habilidad 
   TABLE DATA           0   COPY public.habilidad (id, descrip) FROM stdin;
    public          postgres    false    203   �                 0    46724    vol_habilidad 
   TABLE DATA           ;   COPY public.vol_habilidad (id, id_vol, id_hab) FROM stdin;
    public          postgres    false    204   �       
          0    46711 
   voluntario 
   TABLE DATA           =   COPY public.voluntario (id, nombre, fnacimiento) FROM stdin;
    public          postgres    false    202          �
           2606    46723    habilidad habilidad_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.habilidad
    ADD CONSTRAINT habilidad_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.habilidad DROP CONSTRAINT habilidad_pkey;
       public            postgres    false    203            �
           2606    46728     vol_habilidad vol_habilidad_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.vol_habilidad
    ADD CONSTRAINT vol_habilidad_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.vol_habilidad DROP CONSTRAINT vol_habilidad_pkey;
       public            postgres    false    204            �
           2606    46715    voluntario voluntario_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.voluntario
    ADD CONSTRAINT voluntario_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.voluntario DROP CONSTRAINT voluntario_pkey;
       public            postgres    false    202                  x������ � �            x������ � �      
      x������ � �     