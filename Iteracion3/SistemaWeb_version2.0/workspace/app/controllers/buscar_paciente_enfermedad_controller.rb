class BuscarPacienteEnfermedadController < ApplicationController
  def index
    @enfermedads = Enfermedad.all
  end

  def create
    word = "%#{params[:keyword]}%"
    @enfermedad = Enfermedad.find(params[:id])
    @pacientesMed = current_medico.pacientes
    @pacientesEnf = @enfermedad.pacientes
    
    @pacientes = @pacientesMed & @pacientesEnf
    
    respond_to do |format|
      format.html { redirect_to  buscar_paciente_enfermedad_index_path }
      format.json { render json: @pacientes }
      format.js
    end
  end
end
