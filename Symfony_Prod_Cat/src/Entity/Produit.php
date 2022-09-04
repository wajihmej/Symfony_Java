<?php

namespace App\Entity;

use App\Repository\ProduitRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity(repositoryClass=ProduitRepository::class)
 */
class Produit
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("produit")
     * @Groups("posts:read")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("produit")
     * @Groups("posts:read")
     * @Assert\NotNull
     * @Assert\Length(
     *      min = 3,
     *      max = 10,
     * minMessage = "Le nom doit composer au mois {{ limit }} caractères",
     * maxMessage = "Le nom doit composer au plus {{ limit }} caractères"
     * )
     */
    private $nom;

    /**
     * @ORM\Column(type="float")
     * @Groups("produit")
     * @Groups("posts:read")
     * @Assert\NotNull
     * @Assert\Positive
     */
    private $prix;

    /**
     * @ORM\Column(type="float")
     * @Groups("produit")
     * @Groups("posts:read")
     */
    private $rate;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("produit")
     * @Groups("posts:read")
     */
    private $image;

    /**
     * @ORM\ManyToOne(targetEntity=Categorie::class, inversedBy="produits")
     * @Groups("produit")
     * @Groups("posts:read")
     */
    private $categorie;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getRate(): ?float
    {
        return $this->rate;
    }

    public function setRate(float $rate): self
    {
        $this->rate = $rate;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getCategorie(): ?Categorie
    {
        return $this->categorie;
    }

    public function setCategorie(?Categorie $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }
}
