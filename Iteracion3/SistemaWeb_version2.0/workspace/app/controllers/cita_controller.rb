class CitaController < ApplicationController
  
  def index
    @citas = current_medico.citum.paginate(page: params[:page], :per_page => 25)
  end
  
  
end
