class CitaController < ApplicationController
  
  def index
    @citas = Citum.paginate(page: params[:page], :per_page => 25).all  
  end
  
  
end
