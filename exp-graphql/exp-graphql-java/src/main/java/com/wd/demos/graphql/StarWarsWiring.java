package com.wd.demos.graphql;

import com.wd.demos.graphql.context.Context;
import com.wd.demos.graphql.data.Episode;
import com.wd.demos.graphql.data.FilmCharacter;
import com.wd.demos.graphql.data.Human;
import com.wd.demos.graphql.data.StarWarsData;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;
import graphql.schema.idl.EnumValuesProvider;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * This is our wiring used to put fetching behaviour behind a graphql field.
 */
@Component
public class StarWarsWiring {

    // DataLoaderRegistry is a place to register all data loaders in that needs to be dispatched together
    // in this case there is 1 but you can have many.
    private final DataLoaderRegistry dataLoaderRegistry;

    public StarWarsWiring() {
        this.dataLoaderRegistry = new DataLoaderRegistry();

        // a data loader for characters that points to the character batch loader
        dataLoaderRegistry.register("characters", newCharacterDataLoader());
    }

    @Bean
    public DataLoaderRegistry getDataLoaderRegistry() {
        return dataLoaderRegistry;
    }


    private List<Object> getCharacterDataViaBatchHTTPApi(List<String> keys) {
        return keys.stream().map(StarWarsData::getCharacterData).collect(Collectors.toList());
    }

    // a batch loader function that will be called with N or more keys for batch loading
    private BatchLoader<String, Object> characterBatchLoader = keys -> {

        //
        // we are using multi threading here.  Imagine if getCharacterDataViaBatchHTTPApi was
        // actually a HTTP call - its not here - but it could be done asynchronously as
        // a batch API call say
        //
        //
        // direct return of values
        //CompletableFuture.completedFuture(getCharacterDataViaBatchHTTPApi(keys))
        //
        // or
        //
        // async supply of values
        return CompletableFuture.supplyAsync(() -> getCharacterDataViaBatchHTTPApi(keys));
    };

    // a data loader for characters that points to the character batch loader
    private DataLoader<String, Object> newCharacterDataLoader() {
        return new DataLoader<>(characterBatchLoader);
    }

    // we define the normal StarWars data fetchers so we can point them at our data loader
    DataFetcher humanDataFetcher = environment -> {
        String id = environment.getArgument("id");
        Context ctx = environment.getContext();
        return ctx.getCharacterDataLoader().load(id);
    };


    DataFetcher droidDataFetcher = environment -> {
        String id = environment.getArgument("id");
        Context ctx = environment.getContext();
        return ctx.getCharacterDataLoader().load(id);
    };

    DataFetcher heroDataFetcher = environment -> {
        Context ctx = environment.getContext();
        return ctx.getCharacterDataLoader().load("2001"); // R2D2
    };

    DataFetcher friendsDataFetcher = environment -> {
        FilmCharacter character = environment.getSource();
        List<String> friendIds = character.getFriends();
        Context ctx = environment.getContext();
        return ctx.getCharacterDataLoader().loadMany(friendIds);
    };

    /**
     * Character in the graphql type system is an Interface and something needs
     * to decide that concrete graphql object type to return
     */
    TypeResolver characterTypeResolver = environment -> {
        FilmCharacter character = environment.getObject();
        if (character instanceof Human) {
            return (GraphQLObjectType) environment.getSchema().getType("Human");
        } else {
            return (GraphQLObjectType) environment.getSchema().getType("Droid");
        }
    };

    EnumValuesProvider episodeResolver = Episode::valueOf;
}
