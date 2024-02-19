import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vertical-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/login/theme/lumo/vaadin-login-form.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === 'b1bee3b5a548920513e695c9660d1d3d1b38851adbcb57bee965d2ec5b9d2118') {
    pending.push(import('./chunks/chunk-fb6fd68dadae929c98929f93487254597206f38ab568782fbabe69e258a4ee09.js'));
  }
  if (key === 'ff0ebb1203ed99b591b434b1cebe2015395a622cc82a6b96657006c6cf8e2862') {
    pending.push(import('./chunks/chunk-8d3a863b905e9a9b4ef1f679dab2b53c65bfc3db5a1d573446866def9400bd3b.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;