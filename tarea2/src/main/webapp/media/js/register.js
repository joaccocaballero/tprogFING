 // Cambiar el formulario de registro según el tipo de usuario (Postulante o Empresa)
 document.getElementById('registerAs').addEventListener('change', function() {
    const value = this.value;
    if (value === 'postulante') {
        document.getElementById('postulanteForm').style.display = 'block';
        document.getElementById('empresaForm').style.display = 'none';
    } else {
        
        document.getElementById('postulanteForm').style.display = 'none';
        document.getElementById('empresaForm').style.display = 'block';
    }
});