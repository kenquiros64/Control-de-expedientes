class BuscarmedicoController < ApplicationController
  before_action :authenticate_medico!
  before_filter :must_be_admin
  
  def create
     word = "%#{params[:keyword]}%"
     
    @medicos = Medico.where("codigo LIKE ? OR cedula LIKE ? OR concat_ws(' ',nombre,apellido1) LIKE ? OR concat_ws(' ',apellido1,apellido2) LIKE ?",word,word,word,word)
    
    respond_to do |format|
      format.html { redirect_to buscarmedico_index_path }
      format.json { render json: @medicos }
      format.js
    end
  end

  def index
  end
  
  private
  
    def must_be_admin
      if !current_medico.has_role? :admin
        redirect_to root_path, notice: "Ingreso solo para administradores"
      end
    end
end
