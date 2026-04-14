# 🚀 Configuration des Profils Spring - CareWay Backend

## Vue d'ensemble
Le backend utilise **deux profils Spring** pour gérer différents modes de fonctionnement :
- **DEV** (défaut) : Réinitialise les données à chaque démarrage
- **PROD** : Préserve les changements entre les redémarrages

## 📋 Profil DEV (Développement)

### Utilisation
```bash
# Par défaut (profil DEV automatiquement activé)
java -jar target/backend-0.0.1-SNAPSHOT.jar

# Ou explicitement
java -jar target/backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

### Caractéristiques
- ✅ **data.sql s'exécute à chaque démarrage** (`spring.sql.init.mode=ALWAYS`)
- ✅ **Tables supprimées et recréées** (`spring.jpa.hibernate.ddl-auto=create-drop`)
- ✅ **Données de test toujours disponibles** (56 patients, 56 transporteurs, etc.)
- ✅ **Idéal pour le développement** et les tests
- ❌ Les modifications utilisateur ne persisten PAS après redémarrage

### Configuration
```properties
# application-dev.properties
spring.sql.init.mode=ALWAYS
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

## 🏭 Profil PROD (Production)

### Utilisation
```bash
java -jar target/backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### Caractéristiques
- ✅ **data.sql JAMAIS exécuté** (`spring.sql.init.mode=NEVER`)
- ✅ **Schéma mis à jour parcolonne** (`spring.jpa.hibernate.ddl-auto=update`)
- ✅ **Toutes les modifications persisten** (mot de passe, profil, etc.)
- ✅ **Les données ne sont JAMAIS supprimées entre redémarrages**
- ✅ **Idéal pour la production** et l'utilisation réelle

### Configuration
```properties
# application-prod.properties
spring.sql.init.mode=NEVER
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

## 🔧 Comment changer de profil

### À la compilation
```bash
# DEV
java -jar app.jar

# PROD
java -jar app.jar --spring.profiles.active=prod
```

### Via variable d'environnement
```bash
export SPRING_PROFILES_ACTIVE=prod
java -jar app.jar
```

### Via application.properties
```properties
spring.profiles.active=prod
```

## 📊 Comparaison

| Aspect | DEV | PROD |
|--------|-----|------|
| **data.sql** | S'exécute à CHAQUE redémarrage | N'est JAMAIS exécuté |
| **Tables** | Supprimées et recréées | Mises à jour seulement |
| **Données de test** | Toujours présentes | Préservées à jamais |
| **Modifications peristen** | ❌ Non | ✅ Oui |
| **SQL Logging** | ✅ Actif | ❌ Désactivé |
| **Utilisation** | Développement, tests | Production, utilisation réelle |

## ✅ Test de Validation

```bash
# 1. Lancer en DEV
java -jar app.jar
# Les 56 patients sont là ✅

# 2. Lancer en PROD
java -jar app.jar --spring.profiles.active=prod
# Les patients persistent ✅

# 3. Relancer en DEV
java -jar app.jar
# Les patients sont réinitialisés ✅
```

## 📁 Fichiers de Configuration

- `src/main/resources/application.properties` - Configuration commune
- `src/main/resources/application-dev.properties` - Profil DEV
- `src/main/resources/application-prod.properties` - Profil PROD
- `src/main/resources/data.sql` - Données de test

---

**✨ Résultat : data.sql s'exécute TOUJOURS (DEV), et les changements sont CONSERVÉS (PROD) !**
