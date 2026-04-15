#!/bin/bash
# Script de déploiement pour Render
# Utilisation : ./deploy-render.sh

set -e

echo "🚀 Déploiement CareWay sur Render"
echo "================================"

# Vérifier les prérequis
if ! command -v render &> /dev/null; then
    echo "❌ Render CLI non trouvée. Installez-la : npm install -g @render-oss/render-cli"
    exit 1
fi

# Étape 1: Construire le backend
echo "📦 Construire le backend..."
cd "$(dirname "$0")"
./mvnw clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la compilation du backend"
    exit 1
fi

echo "✅ Backend construit avec succès"

# Étape 2: Vérifier que render.yaml existe
if [ ! -f "render.yaml" ]; then
    echo "❌ render.yaml non trouvé"
    exit 1
fi

echo "✅ render.yaml trouvé"

# Étape 3: Vérifier Git
if ! git status &> /dev/null; then
    echo "❌ Ce n'est pas un repository Git"
    exit 1
fi

# Étape 4: Pousser vers GitHub
echo "📤 Poussant les changements vers GitHub..."
git add -A
git commit -m "chore: Deploy to Render" || true
git push

echo "✅ Code poussé vers GitHub"

# Étape 5: Afficher les instructions Render
echo ""
echo "════════════════════════════════════════════════════════════"
echo "🎯 Prochaines étapes pour Render :"
echo "════════════════════════════════════════════════════════════"
echo ""
echo "1. Allez sur https://render.com"
echo "2. Connectez-vous avec GitHub"
echo "3. Cliquez 'New' → 'Web Service'"
echo "4. Sélectionnez ce repository (BackendCareWay)"
echo "5. Render va utiliser les paramètres de render.yaml"
echo ""
echo "6. Créez une PostgreSQL Database :"
echo "   - Name: careway-db"
echo "   - Database name: careway"
echo ""
echo "7. Ajoutez les variables d'environnement :"
echo "   - GMAIL_PASSWORD: wsqbnpyvgefdlpyx"
echo ""
echo "8. Cliquez 'Deploy'"
echo ""
echo "✅ Votre application sera live dans 2-5 minutes !"
echo "════════════════════════════════════════════════════════════"
