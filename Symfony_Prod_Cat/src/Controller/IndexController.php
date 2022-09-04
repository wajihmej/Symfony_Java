<?php

namespace App\Controller;

use App\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class IndexController extends AbstractController
{
    /**
     * @Route("/", name="app_index")
     */
    public function index(): Response
    {
        $produits=$this->getDoctrine()->getRepository(Produit::class)->findAll();

        return $this->render('index/index.html.twig', [
            'produits' => $produits,
        ]);
    }
    /**
     * @Route("/Admin", name="app_index_admin")
     */
    public function indexAdmin(): Response
    {
        return $this->render('base-back.html.twig', [
            'controller_name' => 'IndexController',
        ]);

        /*
        return $this->render('index/indexAdmin.html.twig', [
            'controller_name' => 'IndexController',
        ]);
        */
    }
}
