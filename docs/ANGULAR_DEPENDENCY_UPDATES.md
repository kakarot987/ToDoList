# Angular dependency patch coordination

The Angular 21 framework uses exact patch-level peer dependencies: for example,
`@angular/common@21.2.20` requires `@angular/core@21.2.20`. A PR that
upgrades only `@angular/common` but leaves other Angular packages at 21.2.17
will fail a clean npm install despite appearing as a one-package fix.

- `.github/dependabot.yml` now requests grouped Angular updates.
- `scripts/check-angular-lock.cjs` verifies the core Angular framework patch
  versions before `npm ci` runs in GitHub Actions.
- For a manual update, update the Angular family together, regenerate
  `package-lock.json` using a normal `npm install`, and verify `npm ci`
  and the Angular production build locally.

The legacy standalone Dependabot PR #25 only upgrades @angular/common and
**must not be merged as written**. Grouping is preventive configuration;
it does not automatically repair that existing PR, and it does not guarantee
that every future security update will be conflict-free.
