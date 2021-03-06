PGDMP         	                x           TB1    12.2    12.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17715    TB1    DATABASE     �   CREATE DATABASE "TB1" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Chile.936' LC_CTYPE = 'Spanish_Chile.936';
    DROP DATABASE "TB1";
                postgres    false            �            1259    17716 	   habilidad    TABLE     d   CREATE TABLE public.habilidad (
    cod integer NOT NULL,
    descrip character varying NOT NULL
);
    DROP TABLE public.habilidad;
       public         heap    postgres    false            �            1259    17732    vol_habilidad    TABLE     �   CREATE TABLE public.vol_habilidad (
    cod integer NOT NULL,
    rut_vol character varying NOT NULL,
    cod_hab integer NOT NULL
);
 !   DROP TABLE public.vol_habilidad;
       public         heap    postgres    false            �            1259    17724 
   voluntario    TABLE     �   CREATE TABLE public.voluntario (
    rut character varying NOT NULL,
    nombre character varying NOT NULL,
    fnacimiento date NOT NULL
);
    DROP TABLE public.voluntario;
       public         heap    postgres    false                      0    17716 	   habilidad 
   TABLE DATA           1   COPY public.habilidad (cod, descrip) FROM stdin;
    public          postgres    false    202   �                 0    17732    vol_habilidad 
   TABLE DATA           >   COPY public.vol_habilidad (cod, rut_vol, cod_hab) FROM stdin;
    public          postgres    false    204   �                 0    17724 
   voluntario 
   TABLE DATA           >   COPY public.voluntario (rut, nombre, fnacimiento) FROM stdin;
    public          postgres    false    203   �       �
           2606    17723    habilidad habilidad_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.habilidad
    ADD CONSTRAINT habilidad_pkey PRIMARY KEY (cod);
 B   ALTER TABLE ONLY public.habilidad DROP CONSTRAINT habilidad_pkey;
       public            postgres    false    202            �
           2606    17739     vol_habilidad vol_habilidad_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.vol_habilidad
    ADD CONSTRAINT vol_habilidad_pkey PRIMARY KEY (cod);
 J   ALTER TABLE ONLY public.vol_habilidad DROP CONSTRAINT vol_habilidad_pkey;
       public            postgres    false    204            �
           2606    17731    voluntario voluntario_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.voluntario
    ADD CONSTRAINT voluntario_pkey PRIMARY KEY (rut);
 D   ALTER TABLE ONLY public.voluntario DROP CONSTRAINT voluntario_pkey;
       public            postgres    false    203            �
           2606    17745    vol_habilidad cod_hab    FK CONSTRAINT     y   ALTER TABLE ONLY public.vol_habilidad
    ADD CONSTRAINT cod_hab FOREIGN KEY (cod_hab) REFERENCES public.habilidad(cod);
 ?   ALTER TABLE ONLY public.vol_habilidad DROP CONSTRAINT cod_hab;
       public          postgres    false    2697    204    202            �
           2606    17740    vol_habilidad rut_vol    FK CONSTRAINT     z   ALTER TABLE ONLY public.vol_habilidad
    ADD CONSTRAINT rut_vol FOREIGN KEY (rut_vol) REFERENCES public.voluntario(rut);
 ?   ALTER TABLE ONLY public.vol_habilidad DROP CONSTRAINT rut_vol;
       public          postgres    false    204    2699    203                  x������ � �            x������ � �            x������ � �     