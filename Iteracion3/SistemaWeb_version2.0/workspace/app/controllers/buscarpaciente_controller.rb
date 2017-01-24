class BuscarpacienteController < ApplicationController
  before_action :authenticate_medico!
  
  def create
    word = "%#{params[:keyword]}%"
     
    @pacientes = current_medico.pacientes.where("cedula LIKE ? OR concat_ws(' ',nombre,apellido1) LIKE ? OR concat_ws(' ',apellido1,apellido2) LIKE ?",word,word,word)
    
    respond_to do |format|
      format.html { redirect_to buscarpaciente_index_path }
      format.json { render json: @pacientes }
      format.js
    end
        
  end
  
  def index
  
  end
  
end
