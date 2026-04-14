# 🚀 Déploiement CareWay sur Render

## 📋 Prérequis

1. **Compte Render** : https://render.com (gratuit)
2. **Dépôt GitHub** avec le code
3. **Deux repositories GitHub** :
   - Backend : `https://github.com/Joris-be/BackendCareWay`
   - Frontend : `https://github.com/chaimaaskri7/front-ptut`

---

## 🔧 Étape 1 : Configuration du Backend (Spring Boot + PostgreSQL)

### Fichiers Créés
- ✅ `render.yaml` - Configuration Render
- ✅ `application-render.properties` - Config PostgreSQL pour Render
- ✅ Dépendance PostgreSQL ajoutée au `pom.xml`

### Profils Disponibles

| Profil | Base de Données | Usage | Commande |
|--------|-----------------|-------|----------|
| **dev** (défaut) | H2 (fichier) | Développement local | `java -jar app.jar` |
| **prod** | H2 (fichier) | Test production local | `java -jar app.jar --spring.profiles.active=prod` |
| **render** | PostgreSQL | Render cloud | `java -jar app.jar --spring.profiles.active=render` |

---

## 📦 Étape 2 : Déployer le Backend sur Render

### Méthode 1 : Interface Web Render (Plus Facile)

1. **Connectez-vous** à https://render.com
2. **Cliquez** sur "New +" → "Web Service"
3. **Connectez votre GitHub** et sélectionnez `BackendCareWay`
4. **Remplissez les informations** :
   - **Name** : `careway-backend`
   - **Environment** : Java
   - **Build Command** : `./mvnw clean package -DskipTests`
   - **Start Command** : `java -jar target/backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=render`
   - **Plan** : Free (ou Starter selon vos besoins)

5. **Créez une PostgreSQL Database** :
   - Cliquez "New +" → "PostgreSQL"
   - **Name** : `careway-db`
   - **Database** : `careway`
   - **Plan** : Free
   - **Region** : Frankfurt ou votre région

6. **Ajoutez les Variables d'Environnement** :
   ```
   SPRING_PROFILES_ACTIVE=render
   GMAIL_PASSWORD=wsqbnpyvgefdlpyx
   ```

7. **Déployez** en cliquant "Deploy"

### Méthode 2 : CLI Render

```bash
# Installer la CLI Render
npm install -g @render-oss/render-cli

# Connectez-vous
render login

# Déployez depuis le dossier backend
cd BackendCareWay
render deploy
```

---

## 🎨 Étape 3 : Déployer le Frontend sur Render

### Créez un `render.yaml` dans le dossier frontend

```yaml
services:
  - type: web
    name: careway-frontend
    plan: free
    runtime: node
    buildCommand: npm install && npm run build
    startCommand: npm run preview
    envVars:
      - key: VITE_API_URL
        value: https://votre-backend-url.onrender.com
```

### Ou via Interface Web

1. **New Web Service** sur Render
2. **Sélectionnez** le dépôt frontend
3. **Runtime** : Node
4. **Build Command** : `npm install && npm run build`
5. **Start Command** : `npm run preview`

---

## 🔐 Étape 4 : Configuration des Variables d'Environnement

### Backend (PostgreSQL)
```
SPRING_DATASOURCE_URL=postgresql://user:pass@host:5432/careway
SPRING_DATASOURCE_USERNAME=<auto-généré par Render>
SPRING_DATASOURCE_PASSWORD=<auto-généré par Render>
SPRING_PROFILES_ACTIVE=render
GMAIL_PASSWORD=wsqbnpyvgefdlpyx
```

### Frontend
```
VITE_API_URL=https://votre-backend-url.onrender.com
VITE_API_PORT=https
```

---

## 🧪 Étape 5 : Test Post-Déploiement

### Vérifier le Backend
```bash
# Remplacez par votre URL Render
curl https://careway-backend.onrender.com/patients/1

# Réponse attendue :
# {"idpatient":1,"prenom":"Jean","nom":"Dupont",...}
```

### Vérifier le Frontend
- Visitez : https://careway-frontend.onrender.com
- Vous devriez voir l'interface CareWay

---

## 💾 Étape 6 : Données Persistantes

### Sur Render
- ✅ **PostgreSQL** fourni par Render (gratuit pendant 90 jours)
- ✅ Les données persisten après redéploiement
- ✅ Accès à pgAdmin si nécessaire

### Premières Données
- Au premier déploiement, `data.sql` s'exécute
- 56 patients, 56 transporteurs, etc. sont chargés
- Les modifications utilisateur persisten

---

## 🛠️ Troubleshooting

### Erreur : "Database connection refused"
- Vérifiez que PostgreSQL est créée sur Render
- Vérifiez les credentials dans les variables d'environnement
- Attendez 1-2 minutes pour que PostgreSQL soit prêt

### Erreur : "Build failed"
- Vérifiez que `./mvnw` a les permissions d'exécution
- Ou utilisez `mvn` à la place

### Logger vs Prod
- **Dev** : `spring.jpa.show-sql=true`
- **Render** : `spring.jpa.show-sql=false` (plus rapide)

---

## 📊 URLs Après Déploiement

```
Backend API    : https://careway-backend.onrender.com
Frontend       : https://careway-frontend.onrender.com
PostgreSQL     : pghost.onrender.com (accès administrateur)
```

---

## ✅ Checklist Déploiement

- [ ] Compte Render créé
- [ ] PostgreSQL créée sur Render
- [ ] Backend déployé avec `--spring.profiles.active=render`
- [ ] Frontend déployé avec API URL correct
- [ ] Variables d'environnement configurées
- [ ] Données test chargées (`data.sql`)
- [ ] API répond à `/patients/1`
- [ ] Frontend affiche l'interface

---

**🚀 Vous êtes prêt pour le cloud ! Bienvenue sur Render !**
