<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Produit;
use App\Form\CategoryType;
use App\Repository\CategorieRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/Categorie")
 */

class CategorieController extends AbstractController
{
    /**
     * @Route("/", name="app_categorie")
     */
    public function index(): Response
    {
        $categories=$this->getDoctrine()->getRepository(Categorie::class)->findAll();
        $produits=$this->getDoctrine()->getRepository(Produit::class)->findAll();

        return $this->render('categorie/index.html.twig', [
            'categories' => $categories,
            'produits' => $produits,
        ]);
    }
    /**
     * @Route("/new", name="app_categorie_new", methods={"GET", "POST"})
     */
    public function new(Request $request, CategorieRepository $categorieRepository): Response
    {
        $categorie = new Categorie();
        $form = $this->createForm(CategoryType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $categorieRepository->add($categorie);
            $this->addFlash(
                'info',
                'Added Successfully'
            );
            return $this->redirectToRoute('app_categorie', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('categorie/add.html.twig', [
            'categorie' => $categorie,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/edit/{id}", name="app_categorie_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Categorie $categorie, CategorieRepository $categorieRepository): Response
    {
        $form = $this->createForm(CategoryType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->addFlash(
                'info-edit',
                'Updated Successfully'
            );

            $categorieRepository->add($categorie);
            return $this->redirectToRoute('app_categorie', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('categorie/edit.html.twig', [
            'categorie' => $categorie,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/delete/{id}", name="app_categorie_delete")
     * method=({"DELETE"})
     */
    public function delete(Request $request, $id)
    {
        $categorie = $this->getDoctrine()->getRepository(Categorie::class)->find($id);

        $entityyManager = $this->getDoctrine()->getManager();
        $entityyManager->remove($categorie);
        $entityyManager->flush();

        $this->addFlash(
            'info-delete',
            'Deleted Successfully'
        );


        return $this->redirectToRoute('app_categorie', [], Response::HTTP_SEE_OTHER);
    }


    /**
     * @Route("/pdf", name="offre_pdf")
     */
    public function PDF()
    {
        //on definit les option du pdf
        $pdfOptions = new Options();
        //police par defaut
        $pdfOptions->set('defaultFont', 'Arial');
        $pdfOptions->setIsRemoteEnabled(true);

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $categories = $this->getDoctrine()->getRepository(Categorie::class)->findAll();



        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('categorie/pdf.html.twig', [
            'categories' => $categories
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);



        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A3', 'paysage');

        // Render the HTML as PDF
        $dompdf->render();



        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("offer.pdf", [
            "Attachment" => false
        ]);
        return new Response();
    }



}
