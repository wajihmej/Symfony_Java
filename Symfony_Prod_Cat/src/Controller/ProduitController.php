<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Form\ProduitType;
use App\Form\RatingPType;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/produits")
 */

class ProduitController extends AbstractController
{
    /**
     * @Route("/", name="app_produit")
     */
    public function index(): Response
    {
        $produits=$this->getDoctrine()->getRepository(Produit::class)->findAll();
        return $this->render('produit/index.html.twig', [
            'produits' => $produits,
        ]);

    }
    /**
     * @Route("/produit/{id}", name="app_produit_single")
     */
    public function produitsingle(Request $request,$id,ProduitRepository $produitRepository): Response
    {
        $produit=$this->getDoctrine()->getRepository(Produit::class)->find($id);

        $form = $this->createForm(RatingPType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $produitRepository->add($produit);
            return $this->redirectToRoute('app_produit_single', [
                'id' =>  $id,
            ], Response::HTTP_SEE_OTHER);
        }

        return $this->render('produit/Singleprod.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);

    }

    /**
     * @Route("/Admin", name="app_produit_Admin")
     */
    public function indexAdmin(): Response
    {
        $produits=$this->getDoctrine()->getRepository(Produit::class)->findAll();
        return $this->render('produit/indexAdmin.html.twig', [
            'produits' => $produits,
        ]);

    }

    /**
     * @Route("/Admin/new", name="app_produit_new", methods={"GET", "POST"})
     */
    public function new(Request $request, ProduitRepository $produitRepository): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            if($file)
            {
                $fileName = md5(uniqid()).'.'.$file->guessExtension();
                try {
                    $file->move(
                        $this->getParameter('images_directory'),
                        $fileName
                    );
                } catch (FileException $e){

                }
                $produit->setImage($fileName);
            }
            else
            {
                $produit->setImage("NoImage.png");
            }

            $produit->setRate(0);
            $produitRepository->add($produit);
            $this->addFlash(
                'info',
                'Added Successfully'
            );
            return $this->redirectToRoute('app_produit_Admin', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('produit/add.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/Admin/edit/{id}", name="app_produit_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            if($file)
            {
                $fileName = md5(uniqid()).'.'.$file->guessExtension();
                try {
                    $file->move(
                        $this->getParameter('images_directory'),
                        $fileName
                    );
                } catch (FileException $e){

                }
                $produit->setImage($fileName);
            }
            $this->addFlash(
                'info-edit',
                'Updated Successfully'
            );

            $produitRepository->add($produit);
            return $this->redirectToRoute('app_produit_Admin', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('produit/edit.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/Admin/delete/{id}", name="app_produit_delete")
     * method=({"DELETE"})
     */
    public function delete(Request $request, $id)
    {
        $prod = $this->getDoctrine()->getRepository(Produit::class)->find($id);

        $entityyManager = $this->getDoctrine()->getManager();
        $entityyManager->remove($prod);
        $entityyManager->flush();

        $this->addFlash(
            'info-delete',
            'Deleted Successfully'
        );


        return $this->redirectToRoute('app_produit', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/r/search_recc", name="search_recc", methods={"GET"})
     */
    public function search_rec(Request $request, NormalizerInterface $Normalizer, ProduitRepository $produitRepository): Response
    {

        $requestString = $request->get('searchValue');
        $requestString3 = $request->get('orderid');

        $prod = $produitRepository->findProduct($requestString, $requestString3);
        $jsoncontentc = $Normalizer->normalize($prod, 'json', ['groups' => 'posts:read']);
        $jsonc = json_encode($jsoncontentc);
        if ($jsonc == "[]") {
            return new Response(null);
        } else {
            return new Response($jsonc);
        }
    }

}
